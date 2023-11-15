package com.example.currencywebcalculator.service;

import java.math.BigDecimal;
import java.util.Set;

public interface ValidationService {
  void validateAmount(BigDecimal amount);

  void validateCurrency(String currencyName);

  void validateCurrencies(String currencyFrom, String currencyTo);

  void checkIfCurrencyExists(Set<String> currencyNames, String currencyName);
}
