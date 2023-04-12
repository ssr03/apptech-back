package com.platform.apptechback.domain.app.repository;

import com.platform.apptechback.domain.app.entity.App;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppRepository extends JpaRepository<App, Long> {
    Page<App> findAll(Pageable pageable);
}