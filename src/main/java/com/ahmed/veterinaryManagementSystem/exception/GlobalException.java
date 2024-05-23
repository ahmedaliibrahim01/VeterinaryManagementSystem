package com.ahmed.veterinaryManagementSystem.exception;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The GlobalException class handles global exceptions and generates appropriate HTTP responses.
 * It is annotated with @ControllerAdvice to apply exception handling globally across all controllers.
 */
@ControllerAdvice
public class GlobalException {
    // Handles EntityNotFoundException and returns a "not found" HTTP response.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ResultInfo.notFoundError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Handles IllegalArgumentException and returns an "already reported" HTTP response.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ResultInfo.alreadyRegistered((ex.getMessage())), HttpStatus.ALREADY_REPORTED);
    }

    // Handles MethodArgumentNotValidException and returns a "bad request" HTTP response with validation errors.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> validationErrorList = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(ResultInfo.validationError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    // Handles HttpMessageNotReadableException and returns a "bad request" HTTP response.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ResultInfo.errorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Handles NonUniqueResultException and returns a "bad request" HTTP response.
    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<Result> NonUniqueResultException(NonUniqueResultException ex) {
        return new ResponseEntity<>(ResultInfo.errorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Handles IllegalStateException and returns an "already reported" HTTP response.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Result> handleIllegalArgumentException(IllegalStateException ex) {
        return new ResponseEntity<>(ResultInfo.alreadyRegistered((ex.getMessage())), HttpStatus.ALREADY_REPORTED);
    }
}
