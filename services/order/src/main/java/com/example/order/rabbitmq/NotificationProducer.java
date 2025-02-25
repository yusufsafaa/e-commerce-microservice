package com.example.order.rabbitmq;

import com.example.order.entities.rabbitmqentities.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation to the queue: {}", orderConfirmation);

        rabbitTemplate.convertAndSend("order-notification-exchange", "order.notification.routing.key", orderConfirmation);
    }
}
