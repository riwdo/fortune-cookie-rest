package com.good.fortunecookierest.repository;

import com.good.fortunecookierest.model.Fortune;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FortuneRepository extends Repository<Fortune, Long> {

    List<Fortune> findAll();

}
