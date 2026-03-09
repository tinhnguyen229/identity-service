package com.devteria.identity_service.exception;

public enum ErrorCode {
    UNAUTHENTICATED(4001, "Unauthenticated"),
    ENUM_KEY_INVALID(4000, "Enum key invalid"),
    UNKNOWN_ERROR(9999, "Unknown Error"),
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXIST(1011, "User not exist"),
    EMAIL_EXISTED(1002, "Email existed"),
    EMAIL_BLANK(1003, "Email is required"),

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
