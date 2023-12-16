package com.platform.apptechback.domain.common.exception;

import com.platform.apptechback.core.exception.ErrorCode;

public class DataInvalidException extends RuntimeException {
    ErrorCode errorCode;

    public DataInvalidException(String message) {
        super(message);
        this.errorCode = ErrorCode.INVALID_DATA;
    }
}
