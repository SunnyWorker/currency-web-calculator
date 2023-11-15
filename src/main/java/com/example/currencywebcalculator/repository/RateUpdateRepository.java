package com.example.currencywebcalculator.repository;

import com.example.currencywebcalculator.entity.RateUpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateUpdateRepository extends JpaRepository<RateUpdateEntity, Long> {}
