package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.ReviewResponse;

import com.platform.apptechback.domain.ranking.dto.UserProfitRequest;
import com.platform.apptechback.domain.ranking.enums.AdminStatus;
import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "apptech_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    private App app;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "rate")
    private Long rate;
    @Column(name = "review")
    private String review;
    @Column(name = "use_yn")
    private boolean useYn;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Review() {

    }

    public ReviewResponse getReviewResponse(){
        return new ReviewResponse(id, app, user, rate, review);
    }

    public void newReview(Long appId, Long userId, Long rate, String review){
        this.userId = userId;
        this.appId = appId;
        this.rate = rate;
        this.review = review;
    }
    public Review(App app, User user, Long rate, String review){
        this.app = app;
        this.user = user;
        this.rate = rate;
        this.review = review;
    }
}
