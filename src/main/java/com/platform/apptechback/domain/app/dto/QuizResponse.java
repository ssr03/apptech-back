package com.platform.apptechback.domain.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String quiz;
    private String answer;
    private Long yesCnt;
    private Long noCnt;
}
