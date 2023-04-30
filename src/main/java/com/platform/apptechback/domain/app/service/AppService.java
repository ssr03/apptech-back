package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.core.util.FileUtil;
import com.platform.apptechback.domain.app.dto.AppRequest;
import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.repository.AppRepository;
import com.platform.apptechback.domain.common.dto.FileDto;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppService {
    private final UserRepository userRepository;
    private final AppRepository appRepository;
    private final FileUtil fileUtil;
    public ResponseEntity<List<AppResponse>> getAppList(String orderBy, int limit, int page) {
       Page<App> appListPage = appRepository.findAll(PageRequest.of(page, limit, Sort.by(orderBy)));
       List<App> appList = appListPage.getContent();
       List<AppResponse> appResponseList = appList.stream()
               .map(m -> new AppResponse(m.getId(),
                       m.getUser().getId(),
                       m.getAppName(),
                       m.getAppLogoFile(),
                       m.getAppIosLink(),
                       m.getAppAndroidLink(),
                       m.getAdminStatus()))
               .collect(Collectors.toList());

       return new ResponseEntity<>(appResponseList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<App> addApp(AppRequest appRequest){
        // 파일 저장
        FileDto file = fileUtil.uploadFile(appRequest.getAppLogoFile());
        User user =
                userRepository.findById(appRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));

        App app = new App();
        app.newApp(user, appRequest, file.getStoredName());

        App savedApp = appRepository.save(app);

        return new ResponseEntity<>(savedApp, HttpStatus.OK);
    }
}
