package com.platform.apptechback.domain.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ReviewRedisService {
    private final ReactiveRedisOperations<String, Object> redisOperations;

    public Mono<Long> getAppReview(Long appId) {
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();
        return hashOperations.get("REVIEW", appId).switchIfEmpty(Mono.defer(() -> {
            System.out.println("Cache Miss");
            // TODO: redis에 데이터 없는 케이스 처리
            return Mono.empty();
        })).map( rate ->
                        (Long)rate
        );
    }

    public Mono<Boolean> saveAppReview(Long appId){
        ReactiveHashOperations<String, Object, Object> hashOperations = redisOperations.opsForHash();
        return hashOperations.putIfAbsent("REVIEW", appId, 1L)
                .filter(result -> !result)
                .flatMap( result ->
                     getAppReview(appId).map( savedRate -> savedRate + 1).flatMap(rate -> {
                                 System.out.println(rate.toString());
                         return hashOperations.put("REVIEW", appId, rate);
                     })
                );
    }
}
