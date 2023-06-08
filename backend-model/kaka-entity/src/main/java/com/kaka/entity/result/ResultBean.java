package com.kaka.entity.result;


import lombok.Data;

@Data
public class ResultBean<T> {
    private int status;
    private boolean success;
    private T message;

    private ResultBean(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }


    public static <T> ResultBean<T> success() {
        return new ResultBean<>(200, true, null);
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<>(200, true, data);
    }

    public static <T> ResultBean<T> failure(int status) {
        return new ResultBean<>(status, false, null);
    }

    public static <T> ResultBean<T> failure(int status, T data) {
        return new ResultBean<>(status, false, data);
    }

}
