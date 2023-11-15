package com.example.currencywebcalculator;

import com.example.currencywebcalculator.dto.request.CreateTransactionRequestDto;
import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.dto.response.RateUpdateDto;
import com.example.currencywebcalculator.entity.BankAccountEntity;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import com.example.currencywebcalculator.entity.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
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

  public static CreateTransactionRequestDto getValidCreateTransactionRequestDto() {
    return CreateTransactionRequestDto.builder()
        .currencyFrom("USD")
        .currencyTo("EUR")
        .userId(1L)
        .amount(BigDecimal.valueOf(100.0))
        .build();
  }

  public static UserEntity getValidUserEntity() {
    return UserEntity.builder()
        .id(1L)
        .first_name("Aka")
        .last_name("Abu")
        .birth_date(LocalDate.now())
        .build();
  }

  public static BankAccountEntity getValidBankAccountFromEntity() {
    return BankAccountEntity.builder()
        .userEntity(getValidUserEntity())
        .currencyEntity(getValidCurrencyEntities().get(0))
        .balance(BigDecimal.valueOf(1000.0))
        .build();
  }

  public static BankAccountEntity getValidBankAccountToEntity() {
    return BankAccountEntity.builder()
        .userEntity(getValidUserEntity())
        .currencyEntity(getValidCurrencyEntities().get(2))
        .balance(BigDecimal.valueOf(1000.0))
        .build();
  }

  public static RateUpdateDto getValidRateUpdateDto() {
    return RateUpdateDto.builder()
        .currencyFrom("USD")
        .currencyTo("EUR")
        .exchangeRateFromTo(BigDecimal.valueOf(0.988))
        .exchangeRateToFrom(BigDecimal.valueOf(1.056))
        .build();
  }

  public static MessageResponseDto getValidTransactionMessageResponseDto() {
    return new MessageResponseDto(MessageResponseDto.TRANSACTION_SUCCESSFUL);
  }
}
