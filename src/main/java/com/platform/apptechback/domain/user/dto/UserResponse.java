package com.platform.apptechback.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String username;
    private String nickname;
    private String email;
    private Boolean adminYn;
}
