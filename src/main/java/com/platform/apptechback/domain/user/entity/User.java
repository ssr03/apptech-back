package com.platform.apptechback.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.platform.apptechback.domain.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @JsonSetter
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @JsonSetter
    @Column(name = "sns_provider")
    private String snsProvider;

    @JsonIgnore
    @JsonSetter
    @Column(name = "sns_id")
    private String snsId;

    @JsonIgnore
    @JsonSetter
    @Column(name = "admin_yn")
    private Boolean adminYn;

    @JsonIgnore
    @JsonSetter
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @JsonSetter
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void newUser(UserRequest userRequest){
        this.username = userRequest.getUsername();
        this.password = userRequest.getPassword();
        this.nickname = userRequest.getNickname();
        this.email = userRequest.getEmail();
        this.snsProvider = userRequest.getSnsProvider();
        this.snsId = userRequest.getSnsId();
        this.adminYn =
                Optional.ofNullable(userRequest.getAdminYn())
                        .orElse(false);
    }
}
