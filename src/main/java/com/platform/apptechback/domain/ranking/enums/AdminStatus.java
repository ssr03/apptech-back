package com.platform.apptechback.domain.ranking.enums;

public enum AdminStatus {

    Y("APPROVAL"),
    H("HOLD"),
    N("REJECT");
    private final String status;

    AdminStatus(final String status) {
        this.status = status;
    }
}
