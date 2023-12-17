package com.platform.apptechback.domain.app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuizRepositoryCustomImpl implements QuizRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Object[]> getQuizList(Long profitId, String date) {
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


        entityManager.close();

        return query.getResultList();
        //emf.close();
    }
}
