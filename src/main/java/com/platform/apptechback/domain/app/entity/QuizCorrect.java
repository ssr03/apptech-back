package com.platform.apptechback.domain.app.entity;

import com.platform.apptechback.domain.app.dto.QuizCorrectRequest;
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
@Table(name = "apptech_profit_quiz_correct")
public class QuizCorrect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_profit_quiz_id")
    private Long appProfitQuizId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "correct_status")
    private String correctStatus;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public QuizCorrect(){

    }
    public QuizCorrect(Long appProfitQuizId, Long userId, String correctStatus){
        this.appProfitQuizId = appProfitQuizId;
        this.userId = userId;
        this.correctStatus = correctStatus;
    }

    public void newQuizCorrect(QuizCorrectRequest quizCorrectRequest){
        this.appProfitQuizId = quizCorrectRequest.getAppProfitQuizId();
        this.userId = quizCorrectRequest.getUserId();
        this.correctStatus = quizCorrectRequest.getCorrectStatus();
    }
}
