package com.ahmed.veterinaryManagementSystem.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Result {
    private boolean success;
    private String message;
    private String httpCode;

    public Result(boolean success, String message, String httpCode) {
        this.success = success;
        this.message = message;
        this.httpCode = httpCode;
    }
}
