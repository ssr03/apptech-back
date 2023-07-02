package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop2ByAppIdAndUseYnIsTrue(Long appId);

    @Query(value = "select avg(c.rate) from apptech_review c where c.app_id = :appId and c.use_yn = true" , nativeQuery = true)
    double getAverageByAppId(@Param(value ="appId") Long appId);

    Optional<Review> findByAppIdAndUserIdAndUseYnIsTrue(Long appId, Long userId);

    List<Review> findByAppIdAndUseYnIsTrue(Long appId);
}