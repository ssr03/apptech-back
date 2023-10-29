package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.ReviewRequest;
import com.platform.apptechback.domain.app.dto.ReviewResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.entity.Review;
import com.platform.apptechback.domain.app.repository.AppRepository;
import com.platform.apptechback.domain.app.repository.ReviewRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final AppRepository appRepository;
    private final ReviewRepository reviewRepository;

    public ResponseEntity<List<ReviewResponse>> get2ReviewList(Long appId) {
       List<Review> reviewList = reviewRepository.findTop2ByAppIdAndUseYnIsTrue(appId);
       List<ReviewResponse> reviewResponseList = reviewList.stream()
               .map(m -> new ReviewResponse(m.getId(),
                       m.getApp(),
                       m.getUser(),
                       m.getRate(),
                       m.getReview()))
               .collect(Collectors.toList());

       return new ResponseEntity<>(reviewResponseList, HttpStatus.OK);
    }

    public String getAverageByAppId(Long appId){
        double average = reviewRepository.getAverageByAppId(appId);
        String averageStr = String.format("%.1f", average);
        return averageStr;
    }

    public Long getRateByAppIdAndUserId(Long appId, Long userId){
        Optional<Review> review = reviewRepository.findByAppIdAndUserIdAndUseYnIsTrue(appId, userId);
        Long rate = 0L;
        if(review.isPresent()){
            rate = review.get().getRate();
        }
        return rate;
    }
    public ResponseEntity<Review> addReview(ReviewRequest reviewRequest){
        User user =
                userRepository.findById(reviewRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        App app =
                appRepository.findById(reviewRequest.getAppId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 앱은 존재 하지 않습니다."));

        //기존 true -> false update
        Optional<Review> review = reviewRepository.findByAppIdAndUserIdAndUseYnIsTrue(reviewRequest.getAppId(), reviewRequest.getUserId());
        if(review.isPresent()){
            Review oldReview = review.get();
            oldReview.setUseYn(false);
            reviewRepository.save(oldReview);
        }

        //새 true 데이터 insert
        Review newReview = new Review();
        newReview.setUser(user);
        newReview.setApp(app);
        newReview.setRate(reviewRequest.getRate());
        newReview.setReview(reviewRequest.getReview());
        newReview.setUseYn(true);
        Review savedReview = reviewRepository.save(newReview);

        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    public ResponseEntity<List<ReviewResponse>> getReviewList(Long appId) {
        List<Review> reviewList = reviewRepository.findByAppIdAndUseYnIsTrue(appId);
        List<ReviewResponse> reviewResponseList = reviewList.stream()
                .map(m -> new ReviewResponse(m.getId(),
                        m.getApp(),
                        m.getUser(),
                        m.getRate(),
                        m.getReview()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(reviewResponseList, HttpStatus.OK);
    }
}
