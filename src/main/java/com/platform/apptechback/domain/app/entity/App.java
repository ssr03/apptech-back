package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.AppResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "apptech_app")
public class App {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "app_name")
    private String appName;
    @Column(name = "app_logo_file")
    private String appLogoFile;
    @Column(name = "app_ios_link")
    private String appIosLink;
    @Column(name = "app_android_link")
    private String appAndroidLink;
    @Column(name = "admin_status")
    private String adminStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public AppResponse getAppResponse(){
        return new AppResponse(id, userId, appName, appLogoFile, appIosLink, appAndroidLink, adminStatus);
    }
}
