package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.ReviewResponse;
import com.platform.apptechback.domain.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getAverageByAppId")
    public String getAverageByAppId(@RequestParam Long appId){
        return reviewService.getAverageByAppId(appId);
    }
}
