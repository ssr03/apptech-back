package com.platform.apptechback.domain.ranking.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import com.platform.apptechback.domain.common.exception.NotFoundException;
import com.platform.apptechback.domain.ranking.dto.UserProfitRequest;
import com.platform.apptechback.domain.ranking.entity.UserProfit;
import com.platform.apptechback.domain.ranking.repository.UserProfitRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserProfitService {
    private final UserProfitRepository userProfitRepository;
    private final UserRepository userRepository;
    private final AppRepository appRepository;

    public ResponseEntity<UserProfit> getUserProfit(Long id){
        UserProfit userProfit = userProfitRepository.findById(id)
                .orElseThrow(()->new NotFoundException(ErrorCode.ENTITY_NOT_FOUND, id + "가 존재 하지 않습니다."));
        return new ResponseEntity<>(userProfit, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserProfit> addUserProfit(UserProfitRequest userProfitRequest){
        // 파일 저장

        // 파일 경로
        String filePath = ""; // userProfitRequest.getProfitImageFile().filename();

        User user =
                userRepository.findById(userProfitRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        App app =
                appRepository.findById(userProfitRequest.getAppId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 앱은 존재 하지 않습니다."));

        UserProfit userProfit = new UserProfit();
        userProfit.newUserProfit(user, userProfitRequest, filePath);

        UserProfit savedUserProfit = userProfitRepository.save(userProfit);

        return new ResponseEntity<>(savedUserProfit, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserProfit> modifyUserProfit(Long id, UserProfitRequest userProfitRequest){
        UserProfit userProfit = userProfitRepository.findById(id)
                .orElseThrow(()->new NotFoundException(ErrorCode.ENTITY_NOT_FOUND, id + "가 존재 하지 않습니다."));

        // 파일 저장

        // 파일 경로
        String filePath = ""; //userProfitRequest.getProfitImageFile().filename();

        userRepository.findById(userProfitRequest.getUserId())
                .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));


        userProfit.modifyUserProfit(userProfitRequest, filePath);

        UserProfit savedUserProfit = userProfitRepository.save(userProfit);
        return new ResponseEntity<>(savedUserProfit, HttpStatus.OK);
    }
}
