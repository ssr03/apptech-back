package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCorrectRequest {
    private long appProfitQuizId;
    private long userId;
    private String correctStatus;
}
