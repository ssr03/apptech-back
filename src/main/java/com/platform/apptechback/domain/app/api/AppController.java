package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class AppController {
    private final AppService appService;

    @GetMapping("/list")
    public ResponseEntity<List<AppResponse>> getAppList() {
        return appService.getAppList();
    }
}
