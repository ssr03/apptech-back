package com.platform.apptechback.domain.user.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.user.dto.UserResponse;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<UserResponse> getUserInfo(String username) {
       UserResponse user =
               userRepository.findByUsername(username)
                       .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, username + "은(는) 존재 하지 않습니다."))
                       .getUserResponse();


        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
