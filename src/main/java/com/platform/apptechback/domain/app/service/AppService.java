package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppService {
    private final AppRepository appRepository;

    public ResponseEntity<List<AppResponse>> getAppList(String orderBy, int limit, int page) {
       Page<App> appListPage = appRepository.findAll(PageRequest.of(page, limit, Sort.by(orderBy)));
       List<App> appList = appListPage.getContent();
       List<AppResponse> appResponseList = appList.stream()
               .map(m -> new AppResponse(m.getId(),
                       m.getUserId(),
                       m.getAppName(),
                       m.getAppLogoFile(),
                       m.getAppIosLink(),
                       m.getAppAndroidLink(),
                       m.getAdminStatus()))
               .collect(Collectors.toList());

       return new ResponseEntity<>(appResponseList, HttpStatus.OK);
    }
}
