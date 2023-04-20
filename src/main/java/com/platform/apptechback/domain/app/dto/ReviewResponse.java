package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long appId;
    private Long userId;
    private Long rate;
    private String review;
}
