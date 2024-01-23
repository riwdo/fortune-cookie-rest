package com.good.fortunecookierest.repository;

import com.good.fortunecookierest.model.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortuneRepository extends JpaRepository<Fortune, Long> {}
