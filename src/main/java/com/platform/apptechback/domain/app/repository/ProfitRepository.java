package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfitRepository extends JpaRepository<Profit, Long> {

}