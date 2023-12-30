package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.AppRequest;
import com.platform.apptechback.domain.app.dto.ReviewRequest;
import com.platform.apptechback.domain.app.dto.ReviewResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.entity.Review;
import com.platform.apptechback.domain.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/get2ReviewList")
    public ResponseEntity<List<ReviewResponse>> get2ReviewList(@RequestParam Long appId) {
        return reviewService.get2ReviewList(appId);
    }

    @GetMapping("/{appId}/rate")
    public ResponseEntity<Mono<String>> getAppReviewRateByAppId(@PathVariable Long appId){
        Mono<String> appReviewRate = reviewService.getAppReviewRateByAppId(appId);
        return new ResponseEntity<>(appReviewRate, HttpStatus.OK);
    }

    @PostMapping("/{appId}/rate")
    public ResponseEntity<Review> saveAppReview(@PathVariable Long appId, @RequestBody ReviewRequest reviewRequest) {
        Review result = reviewService.saveAppReview(appId, reviewRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getRateByAppIdAndUserId")
    public Long getRateByAppIdAndUserId(@RequestParam Long appId, @RequestParam Long userId){
        return reviewService.getRateByAppIdAndUserId(appId, userId);
    }

    @GetMapping("/getReviewList")
    public ResponseEntity<List<ReviewResponse>> getReviewList(@RequestParam Long appId) {
        return reviewService.getReviewList(appId);

    }
}
