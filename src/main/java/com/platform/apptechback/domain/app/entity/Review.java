package com.platform.apptechback.domain.app.entity;


import com.platform.apptechback.domain.app.dto.ReviewResponse;

import com.platform.apptechback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "apptech_review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    private App app;
//    @OneToOne
//    @JoinColumn(name = "app_id", referencedColumnName = "id")
//    private App app;
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
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
