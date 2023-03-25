package com.platform.apptechback.core.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private String message;
    private List<FieldError> errors;

    private ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public static ExceptionResponse of(final HttpStatus status, String message) {
        return new ExceptionResponse(status,message);
    }
}
