package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
