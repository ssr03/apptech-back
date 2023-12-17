package com.platform.apptechback.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    UNKNOWN("Unknown"),
    ENTITY_NOT_FOUND("Entity Not Found"),
    INTERNAL_SERVER_ERROR("Server Error"),
    INVALID_TYPE_VALUE("Invalid Type Value"),
    INVALID_DATA("Invalid Data"),

    // User
    USER_NOT_FOUND("User Not Found"),

    // file
    FILE_ERROR("File Error");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }
}
