package com.example.currencywebcalculator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
  public static final String AMOUNT_MUST_BE_POSITIVE = "The amount must be positive";
  public static final String CURRENCY_NAME_MUST_BE_NON_EMPTY =
      "The name of currency must be non-empty string (%s)";
  public static final String CURRENCY_NAME_MUST_BE_UPPER_CASE =
      "The name of currency must be upper-case string (%s)";
  public static final String CURRENCY_NAME_MUST_ONLY_CONTAIN_CHARACTERS =
      "The name of currency must only contain characters (%s)";
  public static final String CURRENCIES_MUST_BE_DIFFERENT =
      "Currency_from and currency_to must be different (%s)";
  private final HttpStatus httpStatus;

  public BusinessException(final HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
