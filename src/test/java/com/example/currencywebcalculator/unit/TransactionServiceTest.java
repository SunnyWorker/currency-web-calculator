package com.example.currencywebcalculator.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.currencywebcalculator.MockData;
import com.example.currencywebcalculator.dto.request.CreateTransactionRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import com.example.currencywebcalculator.exception.BusinessException;
import com.example.currencywebcalculator.repository.CurrencyRepository;
import com.example.currencywebcalculator.repository.TransactionRepository;
import com.example.currencywebcalculator.repository.UserRepository;
import com.example.currencywebcalculator.service.BankAccountService;
import com.example.currencywebcalculator.service.RateUpdateService;
import com.example.currencywebcalculator.service.ValidationService;
import com.example.currencywebcalculator.service.impl.TransactionServiceImpl;
import com.example.currencywebcalculator.service.impl.ValidationServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
  @Mock private TransactionRepository transactionRepository;
  @Mock private BankAccountService bankAccountService;
  @Mock private RateUpdateService rateUpdateService;
  @Mock private UserRepository userRepository;
  @Mock private CurrencyRepository currencyRepository;
  @Spy private ValidationService validationService = new ValidationServiceImpl();
  @InjectMocks private TransactionServiceImpl underTest;

  private List<CurrencyEntity> currencyEntities;
  private MessageResponseDto messageResponseDto;
  private CreateTransactionRequestDto createTransactionRequestDto;

  @BeforeEach
  public void setUp() {
    currencyEntities = MockData.getValidCurrencyEntities();
    messageResponseDto = MockData.getValidTransactionMessageResponseDto();
    createTransactionRequestDto = MockData.getValidCreateTransactionRequestDto();
  }

  @Test
  void whenCreateTransaction_shouldSuccess() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    when(bankAccountService.getBankAccount(1L, 1L))
        .thenReturn(MockData.getValidBankAccountFromEntity());
    when(bankAccountService.getBankAccount(1L, 3L))
        .thenReturn(MockData.getValidBankAccountToEntity());
    when(userRepository.findById(1L))
        .thenReturn(Optional.ofNullable(MockData.getValidUserEntity()));
    when(rateUpdateService.getLatestRateUpdate(
            MockData.getValidBankAccountFromEntity().getCurrencyEntity().getName(),
            MockData.getValidBankAccountToEntity().getCurrencyEntity().getName()))
        .thenReturn(MockData.getValidRateUpdateDto());
    // then
    MessageResponseDto actualResult = underTest.createTransaction(createTransactionRequestDto);
    assertEquals(messageResponseDto, actualResult);
  }

  @Test
  void whenCreateTransactionWithNotEnoughMoney_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    when(bankAccountService.getBankAccount(1L, 1L))
        .thenReturn(
            MockData.getValidBankAccountFromEntity().toBuilder().balance(BigDecimal.ZERO).build());
    // then
    assertThrows(
        BusinessException.class, () -> underTest.createTransaction(createTransactionRequestDto));
  }

  @Test
  void whenCreateTransactionWithUnknownCurrency_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    assertThrows(
        BusinessException.class,
        () ->
            underTest.createTransaction(
                createTransactionRequestDto.toBuilder().currencyTo("IFFDF").build()));
  }

  @Test
  void whenCreateTransactionWithNegativeAmount_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    assertThrows(
        BusinessException.class,
        () ->
            underTest.createTransaction(
                createTransactionRequestDto.toBuilder().amount(BigDecimal.valueOf(-1.0)).build()));
  }
}
