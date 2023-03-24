package com.platform.apptechback.domain.user.service;

import com.platform.apptechback.domain.user.dto.UserResponse;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<Mono<UserResponse>> getUserInfo(String username) {
       Mono<UserResponse> user =
               userRepository.findByUsername(username)
                       .switchIfEmpty(Mono.error(new RuntimeException("없음")))
                       .map(User::getUserResponse);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
