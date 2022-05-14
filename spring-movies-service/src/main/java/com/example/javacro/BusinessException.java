package com.example.javacro;

public class BusinessException extends RuntimeException {
    public enum Code {
        BP_1, BP_2;
    }
    private final Code businessProcessCode;

    public BusinessException(Code businessProcessCode, String message) {
        super(message);
        this.businessProcessCode = businessProcessCode;
    }

    public Code getBusinessProcessCode() {
        return businessProcessCode;
    }
}
