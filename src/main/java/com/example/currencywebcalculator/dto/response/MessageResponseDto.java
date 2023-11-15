package com.example.currencywebcalculator.dto.response;

public record MessageResponseDto(String message) {
  public static final String UPDATE_CURRENCIES_SUCCESSFUL =
      "All requested currencies are updated successfully";
  public static final String UPDATE_CURRENCIES_WITH_NEW_SUCCESSFUL =
      "Warning that %s currencies were not existing, but all requested currencies are updated successfully";
}
