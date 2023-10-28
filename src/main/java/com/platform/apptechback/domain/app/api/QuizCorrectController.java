package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.QuizCorrectRequest;
import com.platform.apptechback.domain.app.entity.QuizCorrect;
import com.platform.apptechback.domain.app.service.QuizCorrectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quizcorrect")
public class QuizCorrectController {
    private final QuizCorrectService quizCorrectService;

    @PostMapping(value ="/")
    public ResponseEntity<QuizCorrect> addQuizCorrect(@RequestBody QuizCorrectRequest quizCorrectRequest) {
        return quizCorrectService.addQuizCorrect(quizCorrectRequest);
    }
}
