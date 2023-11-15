package com.example.currencywebcalculator.dto.request;

import java.math.BigDecimal;

public record UpdateCurrencyRequestDto(
    String currencyFrom,
    String currencyTo,
    BigDecimal exchangeRateFromTo,
    BigDecimal exchangeRateToFrom,
    BigDecimal officialBankRate) {}
