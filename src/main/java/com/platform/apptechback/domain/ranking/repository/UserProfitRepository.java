package com.platform.apptechback.domain.ranking.repository;

import com.platform.apptechback.domain.ranking.entity.UserProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfitRepository extends JpaRepository<UserProfit, Long> {
}
