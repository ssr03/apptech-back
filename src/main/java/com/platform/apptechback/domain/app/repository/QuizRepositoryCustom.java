package com.platform.apptechback.domain.app.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepositoryCustom {


    List<Object[]> getQuizList(String orderBy, Long profitId, String date);

}
