package com.platform.apptechback.domain.ranking.api;

import com.platform.apptechback.domain.ranking.dto.UserProfitRequest;
import com.platform.apptechback.domain.ranking.entity.UserProfit;
import com.platform.apptechback.domain.ranking.service.UserProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userProfit")
public class UserProfitController {
    private final UserProfitService userProfitService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfit> getUserProfit(@PathVariable Long id) {
        return userProfitService.getUserProfit(id);
    }

    @PostMapping(value ="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProfit> addUserProfit(
            UserProfitRequest userProfitRequest
    ) {
        return userProfitService.addUserProfit(userProfitRequest);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProfit> modifyUserProfit(
            @PathVariable Long id,
            UserProfitRequest userProfitRequest
    ) {
        return userProfitService.modifyUserProfit(id, userProfitRequest);
    }
}
