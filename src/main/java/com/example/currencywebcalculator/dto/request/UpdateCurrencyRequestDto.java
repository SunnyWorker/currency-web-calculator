package com.example.currencywebcalculator.dto.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder(toBuilder = true)
public record UpdateCurrencyRequestDto(
    String currencyFrom,
    String currencyTo,
    BigDecimal exchangeRateFromTo,
    BigDecimal exchangeRateToFrom,
    BigDecimal officialBankRate) {}
