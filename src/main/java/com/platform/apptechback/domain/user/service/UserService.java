package com.platform.apptechback.domain.user.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.user.dto.UserRequest;
import com.platform.apptechback.domain.user.dto.UserResponse;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<UserResponse> getUserInfo(String username) {
       User user =
               userRepository.findByUsername(username)
                       .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, username + "은(는) 존재 하지 않습니다."));
       UserResponse userResponse = new UserResponse();

        return new ResponseEntity<>(userResponse.getUserResponse(user), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<User> addUser(UserRequest userRequest){
        // todo: 회원가입
        User user =  new User();
        user.newUser(userRequest);
        User savedUser = userRepository.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    public boolean getUserYn(String username, String password){
        Optional<User> UserOptional = userRepository.findByUsernameAndPassword(username, password);

        if(UserOptional.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
