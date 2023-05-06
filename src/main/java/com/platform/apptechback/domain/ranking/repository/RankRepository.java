package com.platform.apptechback.domain.ranking.repository;

import com.platform.apptechback.domain.ranking.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
