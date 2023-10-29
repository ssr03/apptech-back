package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.QuizRequest;
import com.platform.apptechback.domain.app.dto.QuizResponse;
import com.platform.apptechback.domain.app.entity.Quiz;
import com.platform.apptechback.domain.app.repository.QuizRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("apptech");
    public ResponseEntity<List<QuizResponse>> getQuizList(Long profitId, String date){
        EntityManager entityManager = emf.createEntityManager();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime startDateTime = LocalDateTime.parse(date + "000000000", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(date + "235959999", formatter);

        String sql = "select apq.id as id," +
                " apq.user_id as userId," +
                " au.username as userName, " +
                " quiz, " +
                " answer, " +
                " coalesce(yes_cnt, 0) as yesCnt, " +
                " coalesce(no_cnt, 0) as noCnt\n" +
                "from apptech_profit_quiz apq\n" +
                "left join\n" +
                "\t(select app_profit_quiz_id, \n" +
                "\t\t\tsum(case when correct_status='YES' then 1 else 0 end) as yes_cnt,\n" +
                "\t\t\tsum(case when correct_status='NO' then 1 else 0 end) as no_cnt\n" +
                "\t\tfrom apptech_profit_quiz_correct\n" +
                "\t\twhere created_at between :startDateTime \n" +
                "\t\t\t\t\tand :endDateTime \n" +
                "\t\tgroup by app_profit_quiz_id ) apqc \n" +
                "on apq.id = apqc.app_profit_quiz_id\n" +
                "inner join app_user au \n" +
                "on apq.user_id = au.id \n" +
                "where apq.app_profit_id = :profitId \n" +
                "and apq.quiz_date between :startDateTime \n" +
                "\t\t\tand :endDateTime";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDateTime", startDateTime);
        query.setParameter("endDateTime", endDateTime);
        query.setParameter("profitId", profitId);
        List<Object[]> results = query.getResultList();

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
        entityManager.close();
        //emf.close();
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
