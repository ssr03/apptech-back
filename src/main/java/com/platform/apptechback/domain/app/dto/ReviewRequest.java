package com.platform.apptechback.domain.app.dto;

import jakarta.persistence.Column;

public class ReviewRequest {
    private Long userId;
    private Long rate;
    private String review;
}
