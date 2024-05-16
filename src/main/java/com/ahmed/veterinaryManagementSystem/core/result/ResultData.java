package com.ahmed.veterinaryManagementSystem.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(boolean success, String message, String httpCode, T data) {
        super(success, message, httpCode);
        this.data = data;
    }
}
