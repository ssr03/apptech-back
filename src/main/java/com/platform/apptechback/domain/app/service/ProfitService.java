package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.ProfitRequest;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.entity.Profit;
import com.platform.apptechback.domain.app.repository.AppRepository;
import com.platform.apptechback.domain.app.repository.ProfitRepository;
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
public class ProfitService {
    private final UserRepository userRepository;
    private final AppRepository appRepository;
    private final ProfitRepository profitRepository;

    @Transactional
    public ResponseEntity<Profit> addProfit(ProfitRequest profitRequest){
        User user =
                userRepository.findById(profitRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        App app =
                appRepository.findById(profitRequest.getAppId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 앱은 존재 하지 않습니다."));


        Profit profit = new Profit();
        profit.newProfit(user, app, profitRequest);

        Profit savedProfit = profitRepository.save(profit);

        return new ResponseEntity<>(savedProfit, HttpStatus.OK);
    }
}
