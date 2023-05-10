package com.platform.apptechback.domain.ranking.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.core.util.FileUtil;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import com.platform.apptechback.domain.common.dto.FileDto;
import com.platform.apptechback.domain.common.exception.NotFoundException;
import com.platform.apptechback.domain.ranking.dto.UserProfitRequest;
import com.platform.apptechback.domain.ranking.entity.UserProfit;
import com.platform.apptechback.domain.ranking.repository.UserProfitRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class UserProfitService {
    private final UserProfitRepository userProfitRepository;
    private final UserRepository userRepository;
    private final AppRepository appRepository;

    private final FileUtil fileUtil;

    public static final String USER_PROFIT_PATH_PREFIX = "PROFIT/";

    public ResponseEntity<UserProfit> getUserProfit(Long id){
        UserProfit userProfit = userProfitRepository.findById(id)
                .orElseThrow(()->new NotFoundException(ErrorCode.ENTITY_NOT_FOUND, id + "가 존재 하지 않습니다."));
        return new ResponseEntity<>(userProfit, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserProfit> addUserProfit(UserProfitRequest userProfitRequest){
        // 파일 저장
        FileDto file = fileUtil.uploadFile(userProfitRequest.getProfitImageFile());

        User user =
                userRepository.findById(userProfitRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        App app =
                appRepository.findById(userProfitRequest.getAppId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 앱은 존재 하지 않습니다."));

        UserProfit userProfit = new UserProfit();
        userProfit.newUserProfit(user, app, userProfitRequest, file.getStoredName());

        UserProfit savedUserProfit = userProfitRepository.save(userProfit);

        return new ResponseEntity<>(savedUserProfit, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserProfit> modifyUserProfit(Long id, UserProfitRequest userProfitRequest){
        UserProfit userProfit = userProfitRepository.findById(id)
                .orElseThrow(()->new NotFoundException(ErrorCode.ENTITY_NOT_FOUND, id + "가 존재 하지 않습니다."));

        String prefix = USER_PROFIT_PATH_PREFIX + userProfitRequest.getAppId() + "/" + userProfit.getProfitDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 파일 저장
        FileDto file = fileUtil.uploadFile(prefix, userProfitRequest.getProfitImageFile());

        userRepository.findById(userProfitRequest.getUserId())
                .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));

        userProfit.modifyUserProfit(userProfitRequest, file.getStoredName());

        UserProfit savedUserProfit = userProfitRepository.save(userProfit);
        return new ResponseEntity<>(savedUserProfit, HttpStatus.OK);
    }
}
