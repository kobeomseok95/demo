package com.example.demo.ex02_EventExample.event;

import com.example.demo.ex02_EventExample.Order;
import org.springframework.stereotype.Component;

@Component
public class KakaotalkSender implements OrderNotifier {

    @Override
    public void sendNotification(Order order) {
        System.out.println("Kakaotalk : " + order.getOrderUser() + "님은 " +
                order.getProduct() + "를 주문하셨습니다. 가격은 " +
                order.getPrice() + "원 입니다.");
    }
}
