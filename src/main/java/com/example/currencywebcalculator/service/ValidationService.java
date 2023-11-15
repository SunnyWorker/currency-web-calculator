package com.example.currencywebcalculator.service;

import java.math.BigDecimal;

public interface ValidationService {
  void validateAmount(BigDecimal amount);

  void validateCurrency(String currencyName);

  void validateCurrencies(String currencyFrom, String currencyTo);
}
