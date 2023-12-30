package com.platform.apptechback.domain.app.service;

import com.platform.apptechback.core.exception.ErrorCode;
import com.platform.apptechback.domain.app.dto.ProfitFavoriteRequest;
import com.platform.apptechback.domain.app.entity.Profit;
import com.platform.apptechback.domain.app.entity.ProfitFavorite;
import com.platform.apptechback.domain.app.repository.ProfitFavoriteRepository;
import com.platform.apptechback.domain.app.repository.ProfitRepository;
import com.platform.apptechback.domain.user.entity.User;
import com.platform.apptechback.domain.user.exception.UserNotFoundException;
import com.platform.apptechback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfitFavoriteService {
    private final ProfitFavoriteRepository profitFavoriteRepository;
    private final UserRepository userRepository;
    private final ProfitRepository profitRepository;
    public ResponseEntity<List<ProfitFavorite>> getProfitFavoriteList(Long userId){
        List<ProfitFavorite> profitFavoriteList = profitFavoriteRepository.findByUserId(userId);
        return new ResponseEntity<>(profitFavoriteList, HttpStatus.OK);
    }

    public boolean getProfitFavorite(Long appProfitId, Long userId){
        Optional<ProfitFavorite> profitFavoriteOptional = profitFavoriteRepository.findByProfitIdAndUserId(appProfitId, userId);

        if(profitFavoriteOptional.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public ResponseEntity<ProfitFavorite> addProfitFavorite(ProfitFavoriteRequest profitFavoriteRequest){
        Profit profit = profitRepository.findById(profitFavoriteRequest.getProfitId())
                .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 수익방안은 존재 하지 않습니다."));

        User user =
                userRepository.findById(profitFavoriteRequest.getUserId())
                        .orElseThrow(()->new UserNotFoundException(ErrorCode.ENTITY_NOT_FOUND, "해당 사용자는 존재 하지 않습니다."));
        ProfitFavorite profitFavorite = new ProfitFavorite();
        profitFavorite.newProfitFavorite(profit, user);

        ProfitFavorite savedProfitFavorite = profitFavoriteRepository.save(profitFavorite);

        return new ResponseEntity<>(savedProfitFavorite, HttpStatus.OK);
    }

    public void deleteProfitFavoriteById(Long profitFavoriteId){
        profitFavoriteRepository.deleteById(profitFavoriteId);
    }

    @Transactional
    public void deleteProfitFavoriteByProfitIdAndUserId(Long appProfitId, Long userId){
        profitFavoriteRepository.deleteByProfitIdAndUserId(appProfitId, userId);
    }

}
