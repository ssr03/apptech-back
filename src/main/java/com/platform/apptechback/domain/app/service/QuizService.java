package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.entity.Quiz;
import com.platform.apptechback.domain.app.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public ResponseEntity<List<Quiz>> getQuizList(Long profitId, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime startDateTime = LocalDateTime.parse(date + "000000000", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(date + "235959999", formatter);
        List<Quiz> quizList = quizRepository.findByProfitId(profitId, startDateTime, endDateTime);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }
}
