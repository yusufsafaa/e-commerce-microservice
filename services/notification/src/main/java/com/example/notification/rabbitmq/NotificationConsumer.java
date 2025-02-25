package com.example.notification.rabbitmq;

import com.example.notification.entities.concrete.Notification;
import com.example.notification.entities.enums.NotificationType;
import com.example.notification.entities.DTOs.OrderConfirmation;
import com.example.notification.entities.DTOs.PaymentConfirmation;
import com.example.notification.entities.repositories.NotificationRepository;
import com.example.notification.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue.payment-success}")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consumed payment success notification: {}", paymentConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_NOTIFICATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        String customerName = paymentConfirmation.getCustomerFirstname() + " " + paymentConfirmation.getCustomerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getTotalAmount(),
                paymentConfirmation.getOrderReference()
        );

    }

    @RabbitListener(queues = "${rabbitmq.queue.order-success}")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consumed order success notification: {}", orderConfirmation);

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_NOTIFICATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        String customerName = orderConfirmation.getCustomer().getFirstname() + " " + orderConfirmation.getCustomer().getLastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }
}
