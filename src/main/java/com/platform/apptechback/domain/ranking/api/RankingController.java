package com.platform.apptechback.domain.ranking.api;

import com.platform.apptechback.domain.ranking.entity.Rank;
import com.platform.apptechback.domain.ranking.enums.RankType;
import com.platform.apptechback.domain.ranking.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankService rankService;

    @GetMapping("")
    public ResponseEntity<List<Rank>> getRanking(
            @RequestParam("appId") Long appId,
            @RequestParam("rankType") RankType rankType) {
        return rankService.getRanking( appId, rankType);
    }
}
