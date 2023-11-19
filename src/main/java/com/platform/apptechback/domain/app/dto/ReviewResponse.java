package com.platform.apptechback.domain.app.dto;

import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private App app;
    private User user;
    private Long rate;
    private String review;
}
