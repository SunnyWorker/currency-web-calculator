package com.example.currencywebcalculator.repository;

import com.example.currencywebcalculator.entity.RateUpdateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateUpdateRepository extends JpaRepository<RateUpdateEntity, Long> {
  @Query(
      "select r from RateUpdateEntity r where ((r.fromCurrencyEntity.id=:currencyFrom AND r.toCurrencyEntity.name=:currencyTo) OR (r.fromCurrencyEntity.name=:currencyTo AND r.toCurrencyEntity.name=:currencyFrom)) order by r.createAt desc")
  Optional<RateUpdateEntity> findLatestRateUpdate(String currencyFrom, String currencyTo);
}
