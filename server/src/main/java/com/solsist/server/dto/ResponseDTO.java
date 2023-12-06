package com.solsist.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private int code;
    private T data;
    private String msg;


    public static <T> ResponseDTO<T> ok(T t) {
        return new ResponseDTO<>(200, t, "success");
    }


    public static <T> ResponseDTO<T> ok(T t, String msg) {
        return new ResponseDTO<>(200, t, msg);
    }

    public static <T> ResponseDTO<T> fail(String msg) {
        return new ResponseDTO<>(-1, null, msg);
    }
    public static <T> ResponseDTO<T> fail(T t) {
        return new ResponseDTO<>(-1, t, "fail");
    }

    public static <T> ResponseDTO<T> fail(T t, String msg) {
        return new ResponseDTO<>(-1, t, msg);
    }

}
