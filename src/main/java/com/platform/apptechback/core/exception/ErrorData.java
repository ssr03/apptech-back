package com.platform.apptechback.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorData {
    String code;
    String message;
    String detail;

    private ErrorData(String code, String message){
        this.code = code;
        this.message = message;
    }
    private ErrorData(String code, String message, String detail){
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static ErrorData of(String code, String message) {
        return new ErrorData(code,message);
    }

    public static ErrorData of(String code, String message, String detail) {
        return new ErrorData(code,message, detail);
    }
}
