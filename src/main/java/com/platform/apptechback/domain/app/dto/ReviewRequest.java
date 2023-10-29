package com.platform.apptechback.domain.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long rate;
    private String review;
}
