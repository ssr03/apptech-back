package com.platform.apptechback.domain.user.entity;


import com.platform.apptechback.domain.user.dto.UserResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Table("app_user")
public class User {
    @Id
    private Long id;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String snsProvider;
    private String snsId;
    private Boolean adminYn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserResponse getUserResponse(){
        return new UserResponse(id, username, nickname, email, adminYn);
    }
}
