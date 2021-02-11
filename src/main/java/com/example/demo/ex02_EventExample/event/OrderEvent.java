package com.example.demo.ex02_EventExample.event;

import com.example.demo.ex02_EventExample.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderEvent {

    private final Order order;
}
