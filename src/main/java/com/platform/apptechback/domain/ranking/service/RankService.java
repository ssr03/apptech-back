package com.platform.apptechback.domain.ranking.service;

import com.platform.apptechback.domain.ranking.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankService {
    private final RankRepository rankRepository;
}
