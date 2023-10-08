package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "select apq.id, apq.user_id , au.username , quiz, answer, coalesce(yes_cnt, 0) as yes_cnt, coalesce(no_cnt, 0) as no_cnt\n" +
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
            "\t\t\tand :endDateTime", nativeQuery = true)
    List<Quiz> findByProfitId(Long profitId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
