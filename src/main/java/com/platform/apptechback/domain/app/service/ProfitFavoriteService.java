package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.domain.app.entity.ProfitFavorite;
import com.platform.apptechback.domain.app.repository.ProfitFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfitFavoriteService {
    private final ProfitFavoriteRepository profitFavoriteRepository;

    public ResponseEntity<List<ProfitFavorite>> getProfitFavoriteList(Long userId){
        List<ProfitFavorite> profitFavoriteList = profitFavoriteRepository.findByUserId(userId);
        return new ResponseEntity<>(profitFavoriteList, HttpStatus.OK);
    }

    public void deleteProfitFavoriteById(Long profitFavoriteId){
        profitFavoriteRepository.deleteById(profitFavoriteId);
    }
}
