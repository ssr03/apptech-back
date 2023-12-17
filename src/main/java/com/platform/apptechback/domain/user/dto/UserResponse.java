package com.platform.apptechback.domain.user.dto;

import com.platform.apptechback.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String username;
    private String nickname;
    private String email;
    private Boolean adminYn;

    public UserResponse getUserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.adminYn = user.getAdminYn();
        return this;
    }
}

