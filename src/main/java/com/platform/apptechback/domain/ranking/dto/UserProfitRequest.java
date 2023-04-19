package com.platform.apptechback.domain.ranking.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class UserProfitRequest {
    private long userId;
    private long appId;
    private String profitDate;
    private Long profit;
    private MultipartFile profitImageFile;
}
