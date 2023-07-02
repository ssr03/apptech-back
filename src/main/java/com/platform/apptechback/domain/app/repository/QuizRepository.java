package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "select * from apptech_profit_quiz apq \n" +
            "where app_profit_id = :profitId \n" +
            "and quiz_date between :startDateTime and :endDateTime", nativeQuery = true)
    List<Quiz> findByProfitId(Long profitId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
