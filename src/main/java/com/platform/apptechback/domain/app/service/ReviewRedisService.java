package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.entity.Review;
import com.platform.apptechback.domain.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ReviewRedisService {
    private static final String REVIEW_KEY = "REVIEW";
    private final ReactiveRedisOperations<String, Object> redisOperations;
    private final ReviewRepository reviewRepository;
    public Mono<String> getAppReviewRateByAppId(Long appId) {
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();
        return hashOperations.get(REVIEW_KEY, appId).switchIfEmpty(Mono.defer(() -> {
            System.out.println("Cache Miss");
            // TODO: redis에 데이터 없는 케이스 처리
            double average = reviewRepository.getAverageByAppId(appId);
            String averageStr = String.format("%.1f", average);

            hashOperations.put(REVIEW_KEY, appId, averageStr).subscribe();

            return Mono.just(averageStr);
        })).map( rate ->
            rate.toString()
        );
    }

    public void saveAppReview(Review review){
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();

        double average = reviewRepository.getAverageByAppId(review.getApp().getId());
        String averageStr = String.format("%.1f", average);

        hashOperations.put(REVIEW_KEY, review.getApp().getId(), averageStr).subscribe();
    }
}
