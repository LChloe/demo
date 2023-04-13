package com.example.demo.base.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResultVo {
    private Boolean resultStatus;
    private String result;
    private String request;
    private int resultCode;

    public ApiResultVo(String request, Boolean resultStatus, int resultCode, String result) {
        this.request = request;
        this.resultStatus = resultStatus;
        this.result = result;
        this.resultCode = resultCode;
    }
    public ApiResultVo(String request, Boolean resultStatus, String result) {
        this.request = request;
        this.resultStatus = resultStatus;
        this.result = result;
        this.resultCode =0;
    }
    public ApiResultVo() {
        this.request = "";
        this.resultStatus = false;
        this.result = "";
        this.resultCode =0;
    }
}
