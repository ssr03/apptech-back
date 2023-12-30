package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.ProfitFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfitFavoriteRepository extends JpaRepository<ProfitFavorite, Long> {
    List<ProfitFavorite> findByUserId(Long userId);

    Optional<ProfitFavorite> findByProfitIdAndUserId(Long appProfitId, Long userId);

    void deleteByProfitIdAndUserId(Long appProfitId, Long userId);
}