package com.example.users.model;

public class UserError implements UserResult, UsersResult{

    private String message;
    private String errorCode;

    public UserError(String message) {
        this.message = message;
        this.errorCode = "INTERNAL_ERROR";
    }

    public UserError(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
