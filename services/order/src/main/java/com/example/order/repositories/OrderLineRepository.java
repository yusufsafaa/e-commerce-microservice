package com.example.order.repositories;

import com.example.order.entities.concretes.OrderLine;
import com.example.order.entities.responses.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
