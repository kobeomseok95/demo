package com.example.demo.ex02_EventExample;

import com.example.demo.ex02_EventExample.event.EmailSender;
import com.example.demo.ex02_EventExample.event.KakaotalkSender;
import com.example.demo.ex02_EventExample.event.OrderCancelEvent;
import com.example.demo.ex02_EventExample.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    public OrderResponseDto order(OrderRequestDto request) {
        Order order = doOrder(request);

        if (!order.isSuccess()) {
            throw new IllegalStateException("주문 실패");
        }

        // 메일만
        eventPublisher.publishEvent(new OrderEvent(order, true, false));

        return modelMapper.map(order, OrderResponseDto.class);
    }

    private Order doOrder(OrderRequestDto req) {
        if (req.getOrderUser().isEmpty() || req.getProduct().isEmpty()) {
            return Order.fail(req.getOrderUser(), req.getProduct(), req.getPrice());
        }

        return Order.success(req.getOrderUser(), req.getProduct(), req.getPrice());
    }

    public OrderCancelResponseDto cancel(OrderCancelRequestDto req) {
        // find 생략
        Order order = Order.cancel(req.getOrderUser(), req.getProduct(), req.getPrice());

        OrderCancelResponseDto response = modelMapper.map(order, OrderCancelResponseDto.class);
        response.setCancelMessage("님의 주문이 취소되었습니다.");

        // 카톡만
        eventPublisher.publishEvent(new OrderCancelEvent(order, false, true));

        return response;
    }
}
