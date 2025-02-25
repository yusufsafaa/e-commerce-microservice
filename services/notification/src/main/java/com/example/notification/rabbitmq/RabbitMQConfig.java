package com.example.notification.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    private final String orderNotificationQueue = "order-notification-queue";
    private final String orderNotificationExchange = "order-notification-exchange";
    private final String orderNotificationRoutingKey = "order.notification.routing.key";
    private final String paymentNotificationQueue = "payment-notification-queue";
    private final String paymentNotificationExchange = "payment-notification-exchange";
    private final String paymentNotificationRoutingKey = "payment.notification.routing.key";

    @Bean
    public Queue paymentNotificationQueue() {
        return new Queue(paymentNotificationQueue, true);
    }

    @Bean
    public Queue orderNotificationQueue() {
        return new Queue(orderNotificationQueue, true);
    }

    @Bean
    public DirectExchange paymentNotificationExchange() {
        return new DirectExchange(paymentNotificationExchange);
    }

    @Bean
    public DirectExchange orderNotificationExchange() {
        return new DirectExchange(orderNotificationExchange);
    }

    @Bean
    public Binding paymentNotificationBinding(Queue paymentNotificationQueue, DirectExchange paymentNotificationExchange) {
        return BindingBuilder.bind(paymentNotificationQueue).to(paymentNotificationExchange).with(paymentNotificationRoutingKey);
    }

    @Bean
    public Binding orderNotificationBinding(Queue orderNotificationQueue, DirectExchange orderNotificationExchange) {
        return BindingBuilder.bind(orderNotificationQueue).to(orderNotificationExchange).with(orderNotificationRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
