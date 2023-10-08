package com.platform.apptechback.domain.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "apptech_profit_quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "quiz")
    private String quiz;
    @Column(name = "answer")
    private String answer;
    @Column(name = "yes_cnt")
    private Long yesCnt;
    @Column(name = "no_cnt")
    private Long noCnt;

    public Quiz(){

    }
    public Quiz(Long user, String userName, String quiz, String answer, Long yesCnt, Long noCnt){
        this.userId = userId;
        this.userName = userName;
        this.quiz = quiz;
        this.answer = answer;
        this.yesCnt = yesCnt;
        this.noCnt = noCnt;
    }
}
