package com.assignment.clush.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestClushControllerAdivce {

    @ExceptionHandler(RestClushException.class)
    public ResponseEntity<RestResponseDto<Void>> handleRestClushException(RestClushException e) {
        String message = e.getMessage();
        return ResponseEntity.internalServerError().body(RestResponseDto.fail(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponseDto<Void>> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.internalServerError().body(RestResponseDto.fail(message));
    }
}
