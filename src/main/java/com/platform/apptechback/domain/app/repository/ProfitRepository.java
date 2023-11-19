package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {
    List<Profit> findByAppId(Long appId);
}