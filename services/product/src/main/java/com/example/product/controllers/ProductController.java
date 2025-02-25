package com.example.product.controllers;

import com.example.product.entities.requests.ProductPurchaseRequest;
import com.example.product.entities.requests.ProductRequest;
import com.example.product.entities.responses.ProductPurchaseResponse;
import com.example.product.entities.responses.ProductResponse;
import com.example.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody @Valid
        List<ProductPurchaseRequest> productPurchaseRequest) {
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequest));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
