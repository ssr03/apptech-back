package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.ReviewResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "apptech_review")
public class Review {
    @Id
    private Long id;

    @Column(name = "app_id")
    private Long appId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "rate")
    private Long rate;
    @Column(name = "review")
    private String review;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ReviewResponse getReviewResponse(){
        return new ReviewResponse(id, appId, userId, rate, review);
    }
}
