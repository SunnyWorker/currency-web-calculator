package com.example.currencywebcalculator.service.impl;

import com.example.currencywebcalculator.dto.request.CreateTransactionRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.dto.response.RateUpdateDto;
import com.example.currencywebcalculator.entity.BankAccountEntity;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import com.example.currencywebcalculator.entity.TransactionEntity;
import com.example.currencywebcalculator.exception.BusinessException;
import com.example.currencywebcalculator.repository.CurrencyRepository;
import com.example.currencywebcalculator.repository.TransactionRepository;
import com.example.currencywebcalculator.repository.UserRepository;
import com.example.currencywebcalculator.service.BankAccountService;
import com.example.currencywebcalculator.service.RateUpdateService;
import com.example.currencywebcalculator.service.TransactionService;
import com.example.currencywebcalculator.service.ValidationService;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final BankAccountService bankAccountService;
  private final RateUpdateService rateUpdateService;
  private final UserRepository userRepository;
  private final CurrencyRepository currencyRepository;
  private final ValidationService validationService;

  @Override
  @Transactional
  public MessageResponseDto createTransaction(
      CreateTransactionRequestDto createTransactionRequestDto) {
    Map<String, CurrencyEntity> currencyEntityMap =
        currencyRepository.findAll().stream()
            .collect(Collectors.toMap(CurrencyEntity::getName, CurrencyEntity::new));

    validateCreateTransactionRequestDto(createTransactionRequestDto, currencyEntityMap.keySet());

    BankAccountEntity bankAccountEntityFrom =
        bankAccountService.getBankAccount(
            createTransactionRequestDto.userId(),
            currencyEntityMap.get(createTransactionRequestDto.currencyFrom()).getId());

    BankAccountEntity bankAccountEntityTo =
        bankAccountService.getBankAccount(
            createTransactionRequestDto.userId(),
            currencyEntityMap.get(createTransactionRequestDto.currencyTo()).getId());

    RateUpdateDto rateUpdateDto =
        rateUpdateService.getLatestRateUpdate(
            createTransactionRequestDto.currencyFrom(), createTransactionRequestDto.currencyTo());

    if (bankAccountEntityFrom.getBalance().compareTo(createTransactionRequestDto.amount()) < 0) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST,
          BusinessException.NOT_ENOUGH_MONEY.formatted(createTransactionRequestDto.amount()));
    }

    if (rateUpdateDto.currencyFrom().equals(createTransactionRequestDto.currencyFrom())) {
      calculateProfitAndSave(
          bankAccountEntityFrom,
          bankAccountEntityTo,
          createTransactionRequestDto.amount(),
          rateUpdateDto.exchangeRateFromTo());
    }

    if (rateUpdateDto.currencyFrom().equals(createTransactionRequestDto.currencyFrom())) {
      calculateProfitAndSave(
          bankAccountEntityFrom,
          bankAccountEntityTo,
          createTransactionRequestDto.amount(),
          rateUpdateDto.exchangeRateToFrom());
    }

    TransactionEntity transactionEntity =
        TransactionEntity.builder()
            .givenMoneyAmount(createTransactionRequestDto.amount())
            .fromCurrencyEntity(currencyEntityMap.get(createTransactionRequestDto.currencyFrom()))
            .toCurrencyEntity(currencyEntityMap.get(createTransactionRequestDto.currencyTo()))
            .userEntity(
                userRepository
                    .findById(createTransactionRequestDto.userId())
                    .orElseThrow(
                        () ->
                            new BusinessException(
                                HttpStatus.BAD_REQUEST,
                                BusinessException.USER_NOT_EXISTS.formatted(
                                    createTransactionRequestDto.userId()))))
            .build();

    transactionRepository.save(transactionEntity);

    return new MessageResponseDto(MessageResponseDto.TRANSACTION_SUCCESSFUL);
  }

  private void validateCreateTransactionRequestDto(
      CreateTransactionRequestDto createTransactionRequestDto, Set<String> currencyNames) {
    validationService.validateAmount(createTransactionRequestDto.amount());
    validationService.validateCurrency(createTransactionRequestDto.currencyFrom());
    validationService.validateCurrency(createTransactionRequestDto.currencyTo());
    validationService.validateCurrencies(
        createTransactionRequestDto.currencyFrom(), createTransactionRequestDto.currencyTo());
    validationService.checkIfCurrencyExists(
        currencyNames, createTransactionRequestDto.currencyFrom());
    validationService.checkIfCurrencyExists(
        currencyNames, createTransactionRequestDto.currencyTo());
  }

  private void calculateProfitAndSave(
      BankAccountEntity bankAccountEntityFrom,
      BankAccountEntity bankAccountEntityTo,
      BigDecimal amount,
      BigDecimal exchangeRate) {
    bankAccountEntityFrom =
        bankAccountEntityFrom.toBuilder()
            .balance(bankAccountEntityFrom.getBalance().subtract(amount))
            .build();
    bankAccountService.saveBankAccount(bankAccountEntityFrom);

    bankAccountEntityTo =
        bankAccountEntityTo.toBuilder()
            .balance(bankAccountEntityTo.getBalance().add(amount.multiply(exchangeRate)))
            .build();
    bankAccountService.saveBankAccount(bankAccountEntityTo);
  }
}
