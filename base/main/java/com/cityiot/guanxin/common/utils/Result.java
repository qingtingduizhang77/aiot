package com.cityiot.guanxin.common.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1064373066413371092L;

    private boolean success;
    private Integer code;// 0成功，负数失败
    private String msg;
    private T value;

    public static Result successResult() {
        return new Result(true, 0, "操作成功");
    }

    public static <K> Result<K> successResult(K value) {
        return new Result(true, 0, "操作成功", value);
    }

    public static Result errorResult() {
        return new Result(false, -1, "操作失败");
    }

    public static <K> Result<K> errorResultWithValue(K value) {
        return new Result(false, -1, "操作失败", value);
    }

    public static Result errorResult(Integer code, String msg) {
        return new Result(false, code, msg);
    }

    public static Result errorResult(String msg) {
        return new Result(false, -1, msg);
    }

    public static Result error404() {
        return new Result(false, -2, "你访问的页面不存在或者暂时无法访问！");
    }

    public static Result paramIllegalResult() {
        return new Result(false, -3, "数据格式有误");
    }

    public static Result exceptionResult() {
        return new Result(false, -4, "系统出错啦");
    }

    public Result() {
    }

    public Result(boolean success, Integer code, String msg, T value) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.value = value;
    }

    public Result(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
