package com.ahmed.veterinaryManagementSystem.core.utils;

import com.ahmed.veterinaryManagementSystem.core.result.ResultData;

public class ResultInfo {
    public static <T>ResultData<T> created(T data){
        return new ResultData<>(true,Messages.CREATED,"201",data);
    }

    public static <T> ResultData<T> validationError(T data){
        return new ResultData<>(false, Messages.VALIDATE_ERROR,"400",data);
    }

    public static <T> ResultData<T> success(T data){
        return new ResultData<>(true, Messages.OK,"200",data);
    }
}
