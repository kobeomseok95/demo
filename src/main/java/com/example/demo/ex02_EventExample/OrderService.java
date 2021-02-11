package com.example.demo.ex02_EventExample;

import com.example.demo.ex02_EventExample.event.EmailSender;
import com.example.demo.ex02_EventExample.event.KakaotalkSender;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ModelMapper modelMapper;
    private final EmailSender emailSender;
    private final KakaotalkSender kakaotalkSender;

    public OrderResponseDto order(OrderRequestDto request) {
        Order order = doOrder(request);

        if (!order.isSuccess()) {
            throw new IllegalStateException("주문 실패");
        }

        emailSender.sendNotification(order);
        kakaotalkSender.sendNotification(order);

        return modelMapper.map(order, OrderResponseDto.class);
    }

    private Order doOrder(OrderRequestDto req) {
        if (req.getOrderUser().isEmpty() || req.getProduct().isEmpty()) {
            return Order.fail(req.getOrderUser(), req.getProduct(), req.getPrice());
        }

        return Order.success(req.getOrderUser(), req.getProduct(), req.getPrice());
    }
}
