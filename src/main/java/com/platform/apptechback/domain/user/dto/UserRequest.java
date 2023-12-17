package com.platform.apptechback.domain.user.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String snsProvider;
    private String snsId;
    private Boolean adminYn;
}
