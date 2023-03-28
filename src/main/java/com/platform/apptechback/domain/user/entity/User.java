package com.platform.apptechback.domain.user.entity;


import com.platform.apptechback.domain.user.dto.UserResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "sns_provider")
    private String snsProvider;
    @Column(name = "sns_id")
    private String snsId;
    @Column(name = "admin_yn")
    private Boolean adminYn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserResponse getUserResponse(){
        return new UserResponse(id, username, nickname, email, adminYn);
    }
}
