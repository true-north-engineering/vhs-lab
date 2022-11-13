package com.truenorth.vhslab.exception;

public class CustomException extends RuntimeException{
    private String exceptionMsg;

    public CustomException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
    public String getExceptionMsg(){
        return this.exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
