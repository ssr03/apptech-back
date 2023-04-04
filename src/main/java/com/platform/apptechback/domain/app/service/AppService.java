package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AppService {
    private final AppRepository appRepository;

    public ResponseEntity<List<AppResponse>> getAppList() {
       List<App> appList = appRepository.findAll();

       List<AppResponse> appResponseList = new ArrayList<>();
       for (App a : appList){
            appResponseList.add(a.getAppResponse());
        }

       return new ResponseEntity<>(appResponseList, HttpStatus.OK);
    }
}
