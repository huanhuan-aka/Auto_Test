package com.auto.common;

import lombok.Data;

@Data
public class ResponseResult {
    private String status; //1表示失败, 0表示成功
    private Object data;
    private String message;

    //成功时返回状态和数据
    public ResponseResult(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    //失败时返回状态和相应信息
    public ResponseResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseResult(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
