package com.example.currencywebcalculator.dto.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder(toBuilder = true)
public record CreateTransactionRequestDto(
    Long userId, String currencyFrom, String currencyTo, BigDecimal amount) {}
