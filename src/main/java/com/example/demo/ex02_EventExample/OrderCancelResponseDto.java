package com.example.demo.ex02_EventExample;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelResponseDto {

    private String orderUser;
    private String cancelMessage;

    public void printMessage() {
        System.out.println(orderUser + cancelMessage);
    }
}
