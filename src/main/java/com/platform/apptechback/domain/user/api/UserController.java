package com.platform.apptechback.domain.user.api;

import com.platform.apptechback.domain.user.dto.UserRequest;
import com.platform.apptechback.domain.user.dto.UserResponse;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/detail")
    public ResponseEntity<UserResponse> getUserDetail(@RequestParam String username) {
        return userService.getUserInfo(username);
    }

    @PostMapping("/")
     public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest){
         return userService.addUser(userRequest);
    }

    @GetMapping("/getUserYn")
    public boolean getUserYn(@RequestParam String username, @RequestParam String password){
        return userService.getUserYn(username, password);
    }
}
