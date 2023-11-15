package com.example.currencywebcalculator.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rate_updates")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RateUpdateEntity extends CreatableEntity {
  @Id
  @SequenceGenerator(
      name = "rate_updates_sequence",
      sequenceName = "rate_updates_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_updates_sequence")
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_from_id", nullable = false)
  private CurrencyEntity fromCurrencyEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_to_id", nullable = false)
  private CurrencyEntity toCurrencyEntity;

  @Column(name = "exchange_rate_from_to", nullable = false)
  private BigDecimal exchangeRateFromTo;

  @Column(name = "exchange_rate_to_from", nullable = false)
  private BigDecimal exchangeRateToFrom;

  @Column(name = "official_bank_rate", nullable = false)
  private BigDecimal officialBankRate;
}
