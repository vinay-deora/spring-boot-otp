package com.example.otp.util;

public class HttpResponse {

    private Object  data;

    private String message;

    private int status;

    public HttpResponse(Object data, String message, int status) {

        this.data = data;
        this.message = message;
        this.status = status;

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
