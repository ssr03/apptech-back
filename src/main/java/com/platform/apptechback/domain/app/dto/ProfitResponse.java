package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfitResponse {
    private Long id;
    private Long appId;
    private Long userId;
    private String profitName;
    private String profitDesc;
    private Long orderNo;
    private boolean quizYn;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String adminStatus;
}
