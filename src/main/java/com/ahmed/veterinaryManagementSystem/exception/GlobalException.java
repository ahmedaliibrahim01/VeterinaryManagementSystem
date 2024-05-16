package com.ahmed.veterinaryManagementSystem.exception;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ResultInfo.notFoundError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ResultInfo.alreadyRegistered((ex.getMessage())), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> validationErrorList = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(ResultInfo.validationError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ResultInfo.errorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
