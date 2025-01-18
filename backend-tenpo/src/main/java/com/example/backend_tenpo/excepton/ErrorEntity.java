package com.example.backend_tenpo.excepton;

public class ErrorEntity {
    private String message;
    private int code;
    private long timeStamp;

    public ErrorEntity(int code, String message, long timeStamp) {
        this.message = message;
        this.code = code;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
}
