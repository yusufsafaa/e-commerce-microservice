package com.example.order.services;

import com.example.order.core.clients.CustomerClient;
import com.example.order.core.clients.PaymentClient;
import com.example.order.core.clients.ProductClient;
import com.example.order.core.exceptions.BusinessException;
import com.example.order.entities.concretes.Order;
import com.example.order.entities.rabbitmqentities.OrderConfirmation;
import com.example.order.entities.requests.OrderLineRequest;
import com.example.order.entities.requests.OrderRequest;
import com.example.order.entities.requests.PaymentRequest;
import com.example.order.entities.requests.PurchaseRequest;
import com.example.order.entities.responses.OrderResponse;
import com.example.order.rabbitmq.NotificationProducer;
import com.example.order.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final ModelMapper modelMapper;
    private final OrderLineService orderLineService;
    private final NotificationProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.getCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order for non-existing customer"));

        var purchasedProducts = productClient.purchaseProducts(orderRequest.getProducts());
        System.out.println("---------1---------");
        var order =orderRepository.save(modelMapper.map(orderRequest, Order.class));
        System.out.println("---------2---------");
        for(PurchaseRequest purchaseRequest: orderRequest.getProducts()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.getProductId(),
                    purchaseRequest.getQuantity()
            ));
        }

        PaymentRequest paymentRequest = new PaymentRequest(
                orderRequest.getTotalAmount(),
                orderRequest.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.getReference(),
                        orderRequest.getTotalAmount(),
                        orderRequest.getPaymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public OrderResponse getOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return modelMapper.map(order, OrderResponse.class);
    }
}
