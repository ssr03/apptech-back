package com.platform.apptechback.domain.ranking.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserProfitRequest {
    private long userId;
    private long appId;
    private String profitDate;
    private Long profit;
    private FilePart profitImageFile;
}
