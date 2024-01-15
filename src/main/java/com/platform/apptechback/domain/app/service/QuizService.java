package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.QuizRequest;
import com.platform.apptechback.domain.app.dto.QuizResponse;
import com.platform.apptechback.domain.app.entity.Quiz;
import com.platform.apptechback.domain.app.repository.QuizRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    public ResponseEntity<List<QuizResponse>> getQuizList(String orderBy, Long profitId, String date){

        List<Object[]> results = quizRepository.getQuizList(orderBy, profitId, date);
        List<QuizResponse> quizList = new ArrayList<QuizResponse>() ;

        for(Object[] result: results){
            QuizResponse quizResponse = new QuizResponse();
            quizResponse.setId((Long)result[0]);
            quizResponse.setUserId((Long)result[1]);
            quizResponse.setUserName((String)result[2]);
            quizResponse.setQuiz((String)result[3]);
            quizResponse.setAnswer((String)result[4]);
            quizResponse.setYesCnt((Long)result[5]);
            quizResponse.setNoCnt((Long)result[6]);
            quizList.add(quizResponse);
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }
    public ResponseEntity<Quiz> addQuiz(QuizRequest quizRequest){
        User user =
                userRepository.findById(quizRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));

        Quiz quiz = new Quiz();
        quiz.newQuiz(quizRequest);

        Quiz savedQuiz = quizRepository.save(quiz);

        return new ResponseEntity<>(savedQuiz, HttpStatus.OK);
    }

}
