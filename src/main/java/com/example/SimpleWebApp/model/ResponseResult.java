package com.example.SimpleWebApp.model;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private T data;
    private String message;
    private Integer status;
}
