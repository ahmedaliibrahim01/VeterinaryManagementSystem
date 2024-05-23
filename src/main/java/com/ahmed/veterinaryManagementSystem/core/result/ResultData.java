package com.ahmed.veterinaryManagementSystem.core.result;

import lombok.Getter;
/**
 * The ResultData class extends the Result class to include data along with the outcome of an operation.
 * @param <T> The type of data associated with the result.
 */
@Getter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(boolean success, String message, String httpCode, T data) {
        super(success, message, httpCode);
        this.data = data;
    }
}
