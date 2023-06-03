package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.ReviewResponse;
import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Review() {

    }

    public ReviewResponse getReviewResponse(){
        return new ReviewResponse(id, app, user, rate, review);
    }

    public Review(App app, User user, Long rate, String review){
        this.app = app;
        this.user = user;
        this.rate = rate;
        this.review = review;
    }
}
