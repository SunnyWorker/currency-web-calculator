package com.example.currencywebcalculator.dto.response;

import java.math.BigDecimal;

public record RateUpdateDto(
    String currencyFrom,
    String currencyTo,
    BigDecimal exchangeRateFromTo,
    BigDecimal exchangeRateToFrom) {}
