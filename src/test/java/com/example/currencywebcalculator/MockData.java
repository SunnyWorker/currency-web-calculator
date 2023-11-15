package com.example.currencywebcalculator;

import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import java.math.BigDecimal;
import java.util.List;

public class MockData {
  public static List<CurrencyEntity> getValidCurrencyEntities() {

    return List.of(
        CurrencyEntity.builder().id(1L).name("USD").build(),
        CurrencyEntity.builder().id(2L).name("GBR").build(),
        CurrencyEntity.builder().id(3L).name("EUR").build());
  }

  public static MessageResponseDto getValidMessageResponseDto() {
    return new MessageResponseDto(MessageResponseDto.UPDATE_CURRENCIES_SUCCESSFUL);
  }

  public static List<UpdateCurrencyRequestDto> getValidUpdateCurrencyRequestDtos() {
    return List.of(
        UpdateCurrencyRequestDto.builder()
            .currencyFrom("USD")
            .currencyTo("EUR")
            .exchangeRateFromTo(BigDecimal.valueOf(0.857))
            .exchangeRateToFrom(BigDecimal.valueOf(1.566))
            .officialBankRate(BigDecimal.valueOf(1.325))
            .build(),
        UpdateCurrencyRequestDto.builder()
            .currencyFrom("GBR")
            .currencyTo("EUR")
            .exchangeRateFromTo(BigDecimal.valueOf(0.993))
            .exchangeRateToFrom(BigDecimal.valueOf(1.007))
            .officialBankRate(BigDecimal.valueOf(1.001))
            .build());
  }

  public static List<UpdateCurrencyRequestDto>
      getValidUpdateCurrencyRequestDtosWithNewCurrencies() {
    return List.of(
        UpdateCurrencyRequestDto.builder()
            .currencyFrom("USD")
            .currencyTo("EUR")
            .exchangeRateFromTo(BigDecimal.valueOf(0.857))
            .exchangeRateToFrom(BigDecimal.valueOf(1.566))
            .officialBankRate(BigDecimal.valueOf(1.325))
            .build(),
        UpdateCurrencyRequestDto.builder()
            .currencyFrom("PUP")
            .currencyTo("EUR")
            .exchangeRateFromTo(BigDecimal.valueOf(0.993))
            .exchangeRateToFrom(BigDecimal.valueOf(1.007))
            .officialBankRate(BigDecimal.valueOf(1.001))
            .build());
  }

  public static UpdateCurrencyRequestDto getInvalidUpdateCurrencyRequestDtoWithEmptyCurrencies() {
    return UpdateCurrencyRequestDto.builder()
        .currencyFrom("")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(0.993))
        .exchangeRateToFrom(BigDecimal.valueOf(1.007))
        .officialBankRate(BigDecimal.valueOf(1.001))
        .build();
  }

  public static UpdateCurrencyRequestDto getInvalidUpdateCurrencyRequestDtoWithNegativeRates() {
    return UpdateCurrencyRequestDto.builder()
        .currencyFrom("USD")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(-0.993))
        .exchangeRateToFrom(BigDecimal.valueOf(1.007))
        .officialBankRate(BigDecimal.valueOf(1.001))
        .build();
  }

  public static UpdateCurrencyRequestDto getInvalidUpdateCurrencyRequestDtoWithLowerCase() {
    return UpdateCurrencyRequestDto.builder()
        .currencyFrom("UgD")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(0.993))
        .exchangeRateToFrom(BigDecimal.valueOf(1.007))
        .officialBankRate(BigDecimal.valueOf(1.001))
        .build();
  }

  public static UpdateCurrencyRequestDto getInvalidUpdateCurrencyRequestDtoWithNonLetters() {
    return UpdateCurrencyRequestDto.builder()
        .currencyFrom("435 ")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(0.993))
        .exchangeRateToFrom(BigDecimal.valueOf(1.007))
        .officialBankRate(BigDecimal.valueOf(1.001))
        .build();
  }

  public static UpdateCurrencyRequestDto getInvalidUpdateCurrencyRequestDtoWithSameCurrencies() {
    return UpdateCurrencyRequestDto.builder()
        .currencyFrom("EUR")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(0.993))
        .exchangeRateToFrom(BigDecimal.valueOf(1.007))
        .officialBankRate(BigDecimal.valueOf(1.001))
        .build();
  }

  public static MessageResponseDto getValidMessageResponseDtoWithNewCurrencies() {
    return new MessageResponseDto(
        MessageResponseDto.UPDATE_CURRENCIES_WITH_NEW_SUCCESSFUL.formatted(List.of("PUP")));
  }
}
