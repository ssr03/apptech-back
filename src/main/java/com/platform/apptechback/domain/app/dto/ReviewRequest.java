package com.platform.apptechback.domain.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@RequiredArgsConstructor
public class ReviewRequest {
    private Long userId;
    private Long appId;
    private Long rate;
    private String review;
}
