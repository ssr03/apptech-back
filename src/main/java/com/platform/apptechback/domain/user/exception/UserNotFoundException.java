package com.platform.apptechback.domain.user.exception;

import com.platform.apptechback.core.exception.ErrorCode;

public class UserNotFoundException extends RuntimeException{
    ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
