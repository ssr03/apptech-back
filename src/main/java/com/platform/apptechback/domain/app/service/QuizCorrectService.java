package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.QuizCorrectRequest;
import com.platform.apptechback.domain.app.entity.QuizCorrect;
import com.platform.apptechback.domain.app.repository.QuizCorrectRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizCorrectService {
    private final UserRepository userRepository;
    private final QuizCorrectRepository quizCorrectRepository;

    public ResponseEntity<QuizCorrect> addQuizCorrect(QuizCorrectRequest quizCorrectRequest){
        User user =
                userRepository.findById(quizCorrectRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));

        QuizCorrect quizCorrect = new QuizCorrect();
        quizCorrect.newQuizCorrect(quizCorrectRequest);

        QuizCorrect savedQuizCorrect = quizCorrectRepository.save(quizCorrect);

        return new ResponseEntity<>(savedQuizCorrect, HttpStatus.OK);
    }

}
