package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {
    private long appProfitId;
    private long userId;
    private LocalDateTime quizDate;
    private String quiz;
    private String answer;
}
