package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.entity.Quiz;
import com.platform.apptechback.domain.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping(value="/getQuizList")
    public ResponseEntity<List<Quiz>> getQuizList(@RequestParam Long profitId, @RequestParam String date){
        return quizService.getQuizList(profitId, date);
    }
}
