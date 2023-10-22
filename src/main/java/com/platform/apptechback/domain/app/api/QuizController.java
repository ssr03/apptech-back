package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.QuizRequest;
import com.platform.apptechback.domain.app.dto.QuizResponse;
import com.platform.apptechback.domain.app.entity.Quiz;
import com.platform.apptechback.domain.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping(value="/getQuizList")
    public ResponseEntity<List<QuizResponse>> getQuizList(@RequestParam Long profitId, @RequestParam String date){
        return quizService.getQuizList(profitId, date);
    }

    @PostMapping(value ="/")
    public ResponseEntity<Quiz> addQuiz(QuizRequest quizRequest) {
        return quizService.addQuiz(quizRequest);
    }
}
