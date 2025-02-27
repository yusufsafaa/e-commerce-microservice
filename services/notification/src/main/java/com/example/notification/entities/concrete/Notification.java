package com.example.notification.entities.concrete;

import com.example.notification.entities.enums.NotificationType;
import com.example.notification.entities.DTOs.OrderConfirmation;
import com.example.notification.entities.DTOs.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class Notification {
    @Id
    private String id;

    private NotificationType notificationType;

    private LocalDateTime notificationDate;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;


}
