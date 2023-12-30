package com.platform.apptechback.domain.app.api;

import com.platform.apptechback.domain.app.dto.ProfitFavoriteRequest;
import com.platform.apptechback.domain.app.entity.ProfitFavorite;
import com.platform.apptechback.domain.app.service.ProfitFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profitFavorite")
public class ProfitFavoriteController {
    private final ProfitFavoriteService profitFavoriteService;

    @GetMapping(value="/getProfitFavoriteList")
    public ResponseEntity<List<ProfitFavorite>> getProfitFavoriteList(@RequestParam Long userId){
        return profitFavoriteService.getProfitFavoriteList(userId);
    }

    @GetMapping(value="/getProfitFavorite")
    public boolean getProfitFavorite(@RequestParam Long appProfitId, @RequestParam Long userId){
        return profitFavoriteService.getProfitFavorite(appProfitId, userId);
    }

    @PostMapping(value ="/")
    public ResponseEntity<ProfitFavorite> addProfitFavorite(@RequestBody ProfitFavoriteRequest profitFavorite) {
        return profitFavoriteService.addProfitFavorite(profitFavorite);
    }

    @DeleteMapping(value="/deleteProfitFavoriteById/{profitFavoriteId}")
    public ResponseEntity<String> deleteProfitFavoriteById(@PathVariable Long profitFavoriteId){
        profitFavoriteService.deleteProfitFavoriteById(profitFavoriteId);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping(value="/deleteProfitFavoriteByProfitIdAndUserId/{appProfitId}/{userId}")
    public ResponseEntity<String> deleteProfitFavoriteByProfitIdAndUserId(@PathVariable Long appProfitId, @PathVariable Long userId){
        profitFavoriteService.deleteProfitFavoriteByProfitIdAndUserId(appProfitId, userId);
        return ResponseEntity.ok("success");
    }
}
