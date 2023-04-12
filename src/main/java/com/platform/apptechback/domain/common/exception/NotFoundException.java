package com.platform.apptechback.domain.common.exception;

import com.platform.apptechback.core.exception.ErrorCode;

public class NotFoundException extends RuntimeException{
    ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
