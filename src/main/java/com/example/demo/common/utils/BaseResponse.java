package com.example.demo.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse {
    private int code;
    private Object data;
    private String message;

    public static BaseResponse success(Object data) {
        return new BaseResponse(200, data, "");
    }
    public static BaseResponse success(Object data, String message) {
        return new BaseResponse(200, data, message);
    }
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }
    public static BaseResponse error(String message) {
        return new BaseResponse(500, null, message);
    }
}
