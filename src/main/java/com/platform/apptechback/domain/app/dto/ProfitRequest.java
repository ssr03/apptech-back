package com.platform.apptechback.domain.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class ProfitRequest {
    private long appId;
    private long userId;
    private String profitName;
    private String profitDesc;
    private Long orderNo;
    private boolean quizYn;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String adminStatus;
}
