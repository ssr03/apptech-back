package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop2ByAppId(Long appId);
}