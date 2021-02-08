package com.example.demo.responseEntityExample;

import lombok.Data;

@Data
public class MyDto {
    private StatusEnum status;
    private String message;
    private Object data;

    public MyDto() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
