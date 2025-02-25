package com.example.payment.rabbitmq;

import com.example.payment.entities.requests.PaymentNotificationRequest;
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

    public void sendNotification(PaymentNotificationRequest paymentNotificationRequest) {
        log.info("Sending payment notification to the queue {}", paymentNotificationRequest);

        rabbitTemplate.convertAndSend("payment-notification-exchange", "payment.notification.routing.key", paymentNotificationRequest);
    }
}
