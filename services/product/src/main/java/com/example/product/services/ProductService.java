package com.example.product.services;

import com.example.product.core.exception.ProductPurchaseException;
import com.example.product.entities.concrete.Product;
import com.example.product.entities.requests.ProductPurchaseRequest;
import com.example.product.entities.requests.ProductRequest;
import com.example.product.entities.responses.ProductPurchaseResponse;
import com.example.product.entities.responses.ProductResponse;
import com.example.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Integer createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        return productRepository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(@Valid List<ProductPurchaseRequest> productPurchaseRequest) {
        List<Integer> productIds = productPurchaseRequest.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some products are not found");
        }

        List<ProductPurchaseRequest> storedRequest = productPurchaseRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();
        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for(int i = 0; i<storedProducts.size(); i++) {
            Product storedProduct = storedProducts.get(i);
            ProductPurchaseRequest request = storedRequest.get(i);

            if(storedProduct.getAvailableQuantity() < request.getQuantity()) {
                throw new ProductPurchaseException("Not enough stock for product with id: " + storedProduct.getId());
            }

            double newStock = storedProduct.getAvailableQuantity() - request.getQuantity();
            storedProduct.setAvailableQuantity(newStock);

            productRepository.save(storedProduct);

            ProductPurchaseResponse response = modelMapper.map(storedProduct, ProductPurchaseResponse.class);
            purchasedProducts.add(response);
        }

        return purchasedProducts;
    }

    public ProductResponse getById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with given id: " + productId));

        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }
}
