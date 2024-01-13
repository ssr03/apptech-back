package com.platform.apptechback.domain.ranking.repository;

import com.platform.apptechback.domain.ranking.entity.Rank;
import com.platform.apptechback.domain.ranking.enums.RankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    @Query(value =
            "SELECT r from Rank r join fetch r.app a join fetch r.user " +
                    "where a.id = :appId and r.type = :rankType")
    List<Rank> findByAppIdAndType(Long appId, RankType rankType);
}
