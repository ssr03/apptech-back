package com.platform.apptechback.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    UNKNOWN("Unknown"),
    ENTITY_NOT_FOUND("Entity Not Found"),
    INTERNAL_SERVER_ERROR("Server Error"),
    INVALID_TYPE_VALUE("Invalid Type Value"),

    // User
    USER_NOT_FOUND("User Not Found");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }
}
