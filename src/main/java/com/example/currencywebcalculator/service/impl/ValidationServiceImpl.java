package com.example.currencywebcalculator.service.impl;

import com.example.currencywebcalculator.exception.BusinessException;
import com.example.currencywebcalculator.service.ValidationService;
import java.math.BigDecimal;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
  @Override
  public void validateAmount(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST, BusinessException.AMOUNT_MUST_BE_POSITIVE);
    }
  }

  @Override
  public void validateCurrency(String currencyName) {
    if (currencyName.trim().isEmpty()) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST,
          BusinessException.CURRENCY_NAME_MUST_BE_NON_EMPTY.formatted(currencyName));
    }

    for (int i = 0; i < currencyName.length(); i++) {
      if (Character.isLowerCase(currencyName.charAt(i)))
        throw new BusinessException(
            HttpStatus.BAD_REQUEST,
            BusinessException.CURRENCY_NAME_MUST_BE_UPPER_CASE.formatted(currencyName));

      if (!Character.isLetter(currencyName.charAt(i))) {
        throw new BusinessException(
            HttpStatus.BAD_REQUEST,
            BusinessException.CURRENCY_NAME_MUST_ONLY_CONTAIN_CHARACTERS.formatted(currencyName));
      }
    }
  }

  @Override
  public void validateCurrencies(String currencyFrom, String currencyTo) {
    if (currencyFrom.equals(currencyTo)) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST,
          BusinessException.CURRENCIES_MUST_BE_DIFFERENT.formatted(currencyFrom));
    }
  }

  @Override
  public void checkIfCurrencyExists(Set<String> currencyNames, String currencyName) {
    if (!currencyNames.contains(currencyName)) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST, BusinessException.CURRENCY_NOT_EXISTS.formatted(currencyName));
    }
  }
}
