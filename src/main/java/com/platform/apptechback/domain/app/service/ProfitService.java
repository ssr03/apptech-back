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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfitService {
    private final UserRepository userRepository;
    private final AppRepository appRepository;
    private final ProfitRepository profitRepository;

    @Transactional
    public ResponseEntity<List<Profit>> addProfit(ProfitRequest[] profitRequestList){
        User user =
                userRepository.findById(profitRequestList[0].getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        App app =
                appRepository.findById(profitRequestList[0].getAppId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 앱은 존재 하지 않습니다."));


        List<Profit> profitList = new ArrayList<>();
        for(int i=0; i<profitRequestList.length; i++){
            Profit profit = new Profit();
            profit.newProfit(user, app, profitRequestList[i]);
            profitList.add(profit);
        }
        List<Profit> savedProfitList = profitRepository.saveAll(profitList);

        return new ResponseEntity<>(savedProfitList, HttpStatus.OK);
    }
}
