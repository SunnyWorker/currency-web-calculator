package com.example.currencywebcalculator.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.currencywebcalculator.MockData;
import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.CurrencyNameResponseDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import com.example.currencywebcalculator.exception.BusinessException;
import com.example.currencywebcalculator.mapper.CurrencyMapper;
import com.example.currencywebcalculator.repository.CurrencyRepository;
import com.example.currencywebcalculator.repository.RateUpdateRepository;
import com.example.currencywebcalculator.service.ValidationService;
import com.example.currencywebcalculator.service.impl.CurrencyServiceImpl;
import com.example.currencywebcalculator.service.impl.ValidationServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {
  @Mock private CurrencyRepository currencyRepository;
  @Spy private ValidationService validationService = new ValidationServiceImpl();
  @Mock private RateUpdateRepository rateUpdateRepository;
  @Spy private CurrencyMapper currencyMapper = Mappers.getMapper(CurrencyMapper.class);
  @InjectMocks private CurrencyServiceImpl underTest;

  private List<CurrencyEntity> currencyEntities;
  private MessageResponseDto messageResponseDto;
  private List<UpdateCurrencyRequestDto> updateCurrencyRequestDtos;

  @BeforeEach
  public void setUp() {
    currencyEntities = MockData.getValidCurrencyEntities();
    updateCurrencyRequestDtos = MockData.getValidUpdateCurrencyRequestDtos();
    messageResponseDto = MockData.getValidMessageResponseDto();
  }

  @Test
  void whenGetAllCurrencies_shouldSuccess() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    List<CurrencyNameResponseDto> actualResult = underTest.getAllCurrencies();
    assertEquals(currencyMapper.toListOfNames(currencyEntities), actualResult);
  }

  @Test
  void whenUpdateCurrencies_shouldSuccess() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    MessageResponseDto actualResult = underTest.updateCurrencies(updateCurrencyRequestDtos);
    assertEquals(messageResponseDto, actualResult);
  }

  @Test
  void whenUpdateCurrenciesWithNewCurrencies_shouldSuccess() {
    updateCurrencyRequestDtos = MockData.getValidUpdateCurrencyRequestDtosWithNewCurrencies();
    messageResponseDto = MockData.getValidMessageResponseDtoWithNewCurrencies();
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    MessageResponseDto actualResult = underTest.updateCurrencies(updateCurrencyRequestDtos);
    assertEquals(messageResponseDto, actualResult);
  }

  @Test
  void whenUpdateCurrenciesWithNegativeRates_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then
    assertThrows(
        BusinessException.class,
        () ->
            underTest.updateCurrencies(
                List.of(MockData.getInvalidUpdateCurrencyRequestDtoWithNegativeRates())));
  }

  @Test
  void whenUpdateCurrenciesWithEmptyCurrencies_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then

    assertThrows(
        BusinessException.class,
        () ->
            underTest.updateCurrencies(
                List.of(MockData.getInvalidUpdateCurrencyRequestDtoWithEmptyCurrencies())));
  }

  @Test
  void whenUpdateCurrenciesWithLowerCaseCurrencies_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then

    assertThrows(
        BusinessException.class,
        () ->
            underTest.updateCurrencies(
                List.of(MockData.getInvalidUpdateCurrencyRequestDtoWithLowerCase())));
  }

  @Test
  void whenUpdateCurrenciesWithNonLetterCurrencies_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then

    assertThrows(
        BusinessException.class,
        () ->
            underTest.updateCurrencies(
                List.of(MockData.getInvalidUpdateCurrencyRequestDtoWithNonLetters())));
  }

  @Test
  void whenUpdateCurrenciesWithSameCurrencies_shouldFail() {
    when(currencyRepository.findAll()).thenReturn(currencyEntities);
    // then

    assertThrows(
        BusinessException.class,
        () ->
            underTest.updateCurrencies(
                List.of(MockData.getInvalidUpdateCurrencyRequestDtoWithSameCurrencies())));
  }
}
