package com.example.payment.services;

import com.example.payment.entities.concrete.Payment;
import com.example.payment.entities.requests.PaymentNotificationRequest;
import com.example.payment.entities.requests.PaymentRequest;
import com.example.payment.rabbitmq.NotificationProducer;
import com.example.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(modelMapper.map(paymentRequest, Payment.class));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.getOrderReference(),
                        paymentRequest.getAmount(),
                        paymentRequest.getPaymentMethod(),
                        paymentRequest.getCustomer().getFirstname(),
                        paymentRequest.getCustomer().getLastname(),
                        paymentRequest.getCustomer().getEmail()
                )
        );

        return payment.getId();
    }

}
