package com.platform.apptechback.domain.app.entity;

import com.platform.apptechback.domain.app.dto.QuizRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apptech_profit_quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_profit_id")
    private Long appProfitId;
    @Column(name = "user_id")
    private Long userId;
    @CreationTimestamp
    @Column(name = "quiz_date")
    private LocalDateTime quizDate;
    @Column(name = "quiz")
    private String quiz;
    @Column(name = "answer")
    private String answer;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Quiz(){

    }
    public Quiz(Long appProfitId, Long userId, LocalDateTime quizDate, String quiz, String answer){
        this.appProfitId = appProfitId;
        this.userId = userId;
        this.quizDate = quizDate;
        this.quiz = quiz;
        this.answer = answer;
    }

    public void newQuiz(QuizRequest quizRequest){
        this.appProfitId = quizRequest.getAppProfitId();
        this.userId = quizRequest.getUserId();
        this.quizDate = quizRequest.getQuizDate();
        this.quiz = quizRequest.getQuiz();
        this.answer = quizRequest.getAnswer();
    }
}
