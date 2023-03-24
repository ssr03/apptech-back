package com.platform.apptechback.domain.user.api;

import com.platform.apptechback.domain.user.dto.UserResponse;
import com.platform.apptechback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/detail")
    public ResponseEntity<Mono<UserResponse>> getUserDetail(@RequestParam String username) {
        return userService.getUserInfo(username);
    }
}
