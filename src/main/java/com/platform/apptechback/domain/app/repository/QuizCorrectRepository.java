package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.QuizCorrect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCorrectRepository extends JpaRepository<QuizCorrect, Long> {

}
