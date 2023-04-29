package com.platform.apptechback.core.exception;

public class FileException extends BadRequestException{
    ErrorCode errorCode;

    public FileException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
