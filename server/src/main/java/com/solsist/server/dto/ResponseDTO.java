package com.solsist.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private int code;
    private T data;
    private String msg;


    public static <T> ResponseDTO<T> ok(Integer code, T t, String msg) {
        return new ResponseDTO<>(code, t, msg);
    }

    public static <T> ResponseDTO<T> ok(T t, String msg) {
        return new ResponseDTO<>(200, t, msg);
    }

    public static <T> ResponseDTO<T> ok(String msg) {
        return new ResponseDTO<>(200, null, msg);
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(200, data, "success");
    }

    public static ResponseDTO ok() {
        return new ResponseDTO<>(200, null, "success");
    }


    public static <T> ResponseDTO<T> fail(String msg) {
        return new ResponseDTO<>(-1, null, msg);
    }

    public static ResponseDTO fail(int code, String msg) {
        return new ResponseDTO<>(code, null, msg);
    }

    public static ResponseDTO fail() {
        return new ResponseDTO<>(-1, null, "fail");
    }

    public static <T> ResponseDTO<T> fail(T t) {
        return new ResponseDTO<>(-1, t, "fail");
    }

    public static <T> ResponseDTO<T> fail(T t, String msg) {
        return new ResponseDTO<>(-1, t, msg);
    }

}
