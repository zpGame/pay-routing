package com.hunk.route.interfaces.facade;

import lombok.Getter;

/**
 * @author hunk
 * @date 2022/3/18
 *     <p>
 */
@Getter
public class Result<T> {

    private final String code;

    private final String message;

    private final T data;

    private static final String FAIL = "999999";
    private static final String SUCCESS = "000000";

    public static <E> Result<E> tResult(String code, String message, E data) {
        return new Result<>(code, message, data);
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <E> Result<E> fail() {
        return Result.tResult(FAIL, "系统异常", null);
    }

    public static <E> Result<E> ok(E e) {
        return Result.tResult(SUCCESS, "操作成功", e);
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.code);
    }

    public boolean isFail() {
        return FAIL.equals(this.code);
    }
}
