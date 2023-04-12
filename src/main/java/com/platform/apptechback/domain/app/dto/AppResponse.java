package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AppResponse {
    private Long id;

    private Long userId;
    private String appName;
    private String appLogoFile;
    private String appIosLink;
    private String appAndroidLink;
    private String adminStatus;
}
