package com.example.demo.util;

public class ErrorResponse {

    String message="";
    Integer code;
    
    public ErrorResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }


}
