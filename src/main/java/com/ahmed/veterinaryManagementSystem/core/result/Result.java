package com.ahmed.veterinaryManagementSystem.core.result;

import lombok.Getter;
/**
 * The Result class represents the outcome of an operation, indicating success or failure.
 */
@Getter
public class Result {
    private boolean success;
    private String message;
    private String code;

    public Result(boolean success, String message, String code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }
}
