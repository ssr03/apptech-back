package com.platform.apptechback.core.exception;

import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e, ServerWebExchange exchange) {
        final ErrorData error = ErrorData.of(ErrorCode.INTERNAL_SERVER_ERROR.name(), e.getMessage());
        final ExceptionResponse response = ExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,error);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomException(BadRequestException e, ServerWebExchange exchange) {
        final ErrorData error = ErrorData.of(ErrorCode.UNKNOWN.name(), e.getMessage());
        final ExceptionResponse response = ExceptionResponse.of(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
        final ErrorData error = ErrorData.of(ErrorCode.USER_NOT_FOUND.name(), e.getMessage());
        final ExceptionResponse response = ExceptionResponse.of(HttpStatus.OK, error);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
