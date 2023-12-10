package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.AppRequest;
import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apptech_app")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_id")
    List<Review> reviews = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public AppResponse getAppResponse(){
        return new AppResponse(id, user.getId(), appName, appLogoFile, appIosLink, appAndroidLink, adminStatus);
    }

    public App(User user, String appName, String appLogoFile, String appIosLink, String appAndroidLink, String adminStatus){
        this.user = user;
        this.appName = appName;
        this.appLogoFile = appLogoFile;
        this.appIosLink = appIosLink;
        this.appAndroidLink = appAndroidLink;
        this.adminStatus = adminStatus;
    }

    public void newApp(User user, AppRequest appRequest, String appLogoFilePath){
        this.user = user;
        this.appName = appRequest.getAppName();
        this.appLogoFile = appLogoFilePath;
        this.appIosLink = appRequest.getAppIosLink();
        this.appAndroidLink = appRequest.getAppAndroidLink();
        this.adminStatus = "WAITING";
    }
}
