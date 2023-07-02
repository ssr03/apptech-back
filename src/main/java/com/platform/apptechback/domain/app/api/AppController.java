package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.AppRequest;
import com.platform.apptechback.domain.app.dto.AppResponse;
import com.platform.apptechback.domain.app.entity.App;
import com.platform.apptechback.domain.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class AppController {
    private final AppService appService;

    @GetMapping("/list")
    public ResponseEntity<Page<App>> getAppList(@RequestParam String orderBy,
                                                @RequestParam int limit,
                                                @RequestParam int page) {
        return appService.getAppList(orderBy, limit, page);
    }

    @PostMapping(value ="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<App> addApp(AppRequest appRequest) {
        return appService.addApp(appRequest);
    }
}
