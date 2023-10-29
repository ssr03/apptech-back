package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.dto.ReviewRequest;
import com.platform.apptechback.domain.app.dto.ReviewResponse;
import com.platform.apptechback.domain.app.entity.Review;
import com.platform.apptechback.domain.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewRedisService reviewRedisService;

    public ResponseEntity<List<ReviewResponse>> get2ReviewList(Long appId) {
       List<Review> reviewList = reviewRepository.findTop2ByAppId(appId);
       List<ReviewResponse> reviewResponseList = reviewList.stream()
               .map(m -> new ReviewResponse(m.getId(),
                       m.getAppId(),
                       m.getUserId(),
                       m.getRate(),
                       m.getReview()))
               .collect(Collectors.toList());

       return new ResponseEntity<>(reviewResponseList, HttpStatus.OK);
    }

    public Mono<Long> getAppReview(Long appId) {
        return reviewRedisService.getAppReview(appId);
    }

    @Transactional
    public Review saveAppReview(Long appId, ReviewRequest reviewRequest){
        // review 저장
        Review review = new Review();
        review.newReview(appId, reviewRequest.getUserId(), reviewRequest.getRate(), reviewRequest.getReview());
        Review savedReview = reviewRepository.save(review);
        // 레디스 저장
        reviewRedisService.saveAppReview(savedReview);
        return savedReview;
    }
}
