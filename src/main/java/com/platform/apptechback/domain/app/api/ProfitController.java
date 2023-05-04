package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.ProfitRequest;
import com.platform.apptechback.domain.app.entity.Profit;
import com.platform.apptechback.domain.app.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profit")
public class ProfitController {
    private final ProfitService profitService;

    @PostMapping(value ="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Profit> addProfit(ProfitRequest profitRequest) {
        return profitService.addProfit(profitRequest);
    }
}
