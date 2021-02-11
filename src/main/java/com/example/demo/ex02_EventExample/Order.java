package com.example.demo.ex02_EventExample;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderUser;
    private String product;
    private int price;
    private boolean success;

    public static Order success(final String user, final String product, final int price) {
        return new Order(user, product, price, true);
    }

    public static Order fail(final String user, final String product, final int price) {
        return new Order(user, product, price, false);
    }
}
