package com.platform.apptechback.domain.ranking.service;

import com.platform.apptechback.domain.ranking.entity.Rank;
import com.platform.apptechback.domain.ranking.enums.RankType;
import com.platform.apptechback.domain.ranking.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RankService {
    private final RankRepository rankRepository;

    public ResponseEntity<List<Rank>> getRanking(Long appId, RankType rankType){
        List<Rank> rankingList =
                rankRepository.findByAppIdAndType(appId, rankType);
        return new ResponseEntity<>(rankingList, HttpStatus.OK);
    }
}
