package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizRepositoryCustom {

}
