package com.example.demo.base.result;


/**
 * 返回结构体  code为0表示请求成功 非REST
 */
public class ResultVo<T> {
    private int code;
    private String errorMsg;
    private T data;

    public ResultVo() {
    }

    public ResultVo(int code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ResultVo(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
