package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.entity.Review;
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

    public Mono<Long> getAppReview(Long appId) {
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();
        return hashOperations.get(REVIEW_KEY, appId).switchIfEmpty(Mono.defer(() -> {
            System.out.println("Cache Miss");
            // TODO: redis에 데이터 없는 케이스 처리
            return Mono.empty();
        })).map( rate ->
                Long.parseLong(rate.toString())
        );
    }

    public void saveAppReview(Review review){
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();
        hashOperations.putIfAbsent(REVIEW_KEY, review.getApp().getId(), review.getRate())
                .filter(result -> !result)
                .flatMap(result ->
                        getAppReview(review.getApp().getId()).map(savedRate -> savedRate + review.getRate())
                                .flatMap(rate -> hashOperations.put(REVIEW_KEY, review.getApp().getId(), rate))
                ).subscribe();
    }
}
