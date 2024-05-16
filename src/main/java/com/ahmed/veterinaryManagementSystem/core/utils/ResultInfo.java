package com.ahmed.veterinaryManagementSystem.core.utils;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.response.CursorResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.customer.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResultInfo {
    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Messages.CREATED, "201", data);
    }

    public static <T> ResultData<T> validationError(T data) {
        return new ResultData<>(false, Messages.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Messages.OK, "200", data);
    }

    public static Result ok() {
        return new Result(true, Messages.OK, "200");
    }

    public static Result notFoundError(String message) {
        return new Result(false, message, "404");
    }

    public static Result alreadyRegistered(String message) {
        return new Result(false, message, "500");
    }

    public static Result errorMessage(String message) {
        return new Result(false, message, "400");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData) {
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElement(pageData.getTotalElements());
        return ResultInfo.success(cursor);
    }
}
