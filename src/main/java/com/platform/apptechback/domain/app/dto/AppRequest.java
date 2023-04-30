package com.platform.apptechback.domain.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@RequiredArgsConstructor
public class AppRequest {
    private Long userId;
    private String appName;
    private MultipartFile appLogoFile;
    private String appIosLink;
    private String appAndroidLink;
    private String adminStatus;
}
