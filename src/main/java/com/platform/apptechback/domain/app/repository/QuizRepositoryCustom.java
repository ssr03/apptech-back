package com.platform.apptechback.domain.app.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepositoryCustom {


    List<Object[]> getQuizList(Long profitId, String date);

}
