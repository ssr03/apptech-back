package com.platform.apptechback.core.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private ErrorData error;

    private ExceptionResponse(HttpStatus status, ErrorData error) {
        this.status = status;
        this.error = error;
    }

    public static ExceptionResponse of(HttpStatus status, ErrorData error) {
        return new ExceptionResponse(status,error);
    }
}
