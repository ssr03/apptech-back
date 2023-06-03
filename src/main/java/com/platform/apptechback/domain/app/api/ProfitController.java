package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.ProfitRequest;
import com.platform.apptechback.domain.app.entity.Profit;
import com.platform.apptechback.domain.app.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profit")
public class ProfitController {
    private final ProfitService profitService;

    @PostMapping(value ="/")
    public ResponseEntity<List<Profit>> addProfit(@RequestBody ProfitRequest[] profitRequestList) {
        return profitService.addProfit(profitRequestList);
    }

    @GetMapping(value="/getProfitList")
    public ResponseEntity<List<Profit>> getProfitList(@RequestParam Long appId){
        return profitService.getProfitList(appId);
    }

}
