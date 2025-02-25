package com.example.order.services;

import com.example.order.entities.concretes.OrderLine;
import com.example.order.entities.requests.OrderLineRequest;
import com.example.order.entities.responses.OrderLineResponse;
import com.example.order.repositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = modelMapper.map(orderLineRequest, OrderLine.class);
        return orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> getAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLine -> modelMapper.map(orderLine, OrderLineResponse.class))
                .toList();
    }
}
