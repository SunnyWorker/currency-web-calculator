package com.example.currencywebcalculator.service.impl;

import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.CurrencyNameResponseDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import com.example.currencywebcalculator.entity.RateUpdateEntity;
import com.example.currencywebcalculator.mapper.CurrencyMapper;
import com.example.currencywebcalculator.repository.CurrencyRepository;
import com.example.currencywebcalculator.repository.RateUpdateRepository;
import com.example.currencywebcalculator.service.CurrencyService;
import com.example.currencywebcalculator.service.ValidationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyRepository currencyRepository;
  private final ValidationService validationService;
  private final RateUpdateRepository rateUpdateRepository;
  private final CurrencyMapper currencyMapper;

  @Override
  public List<CurrencyNameResponseDto> getAllCurrencies() {
    return currencyMapper.toListOfNames(currencyRepository.findAll());
  }

  @Override
  @Transactional
  public MessageResponseDto updateCurrencies(
      List<UpdateCurrencyRequestDto> updateCurrencyRequestDtos) {

    Map<String, CurrencyEntity> currencyEntityMap =
        currencyRepository.findAll().stream()
            .collect(Collectors.toMap(CurrencyEntity::getName, CurrencyEntity::new));

    List<String> absentCurrenciesNames = new ArrayList<>();

    updateCurrencyRequestDtos.stream()
        .forEach(
            updateCurrencyRequestDto -> {
              validateUpdateCurrencyRequestDto(updateCurrencyRequestDto);
              if (!currencyEntityMap.containsKey(updateCurrencyRequestDto.currencyFrom())) {
                absentCurrenciesNames.add(updateCurrencyRequestDto.currencyFrom());
                currencyEntityMap.put(
                    updateCurrencyRequestDto.currencyFrom(),
                    currencyRepository.save(
                        CurrencyEntity.builder()
                            .name(updateCurrencyRequestDto.currencyFrom())
                            .build()));
              }

              if (!currencyEntityMap.containsKey(updateCurrencyRequestDto.currencyTo())) {
                absentCurrenciesNames.add(updateCurrencyRequestDto.currencyTo());
                currencyEntityMap.put(
                    updateCurrencyRequestDto.currencyTo(),
                    currencyRepository.save(
                        CurrencyEntity.builder()
                            .name(updateCurrencyRequestDto.currencyTo())
                            .build()));
              }

              RateUpdateEntity rateUpdateEntity =
                  RateUpdateEntity.builder()
                      .fromCurrencyEntity(
                          currencyEntityMap.get(updateCurrencyRequestDto.currencyFrom()))
                      .toCurrencyEntity(
                          currencyEntityMap.get(updateCurrencyRequestDto.currencyTo()))
                      .exchangeRateFromTo(updateCurrencyRequestDto.exchangeRateFromTo())
                      .exchangeRateToFrom(updateCurrencyRequestDto.exchangeRateToFrom())
                      .officialBankRate(updateCurrencyRequestDto.officialBankRate())
                      .build();

              rateUpdateRepository.save(rateUpdateEntity);
            });

    if (absentCurrenciesNames.isEmpty())
      return new MessageResponseDto(MessageResponseDto.UPDATE_CURRENCIES_SUCCESSFUL);

    return new MessageResponseDto(
        MessageResponseDto.UPDATE_CURRENCIES_WITH_NEW_SUCCESSFUL.formatted(
            absentCurrenciesNames.toString()));
  }

  private void validateUpdateCurrencyRequestDto(UpdateCurrencyRequestDto updateCurrencyRequestDto) {
    validationService.validateCurrency(updateCurrencyRequestDto.currencyFrom());
    validationService.validateCurrency(updateCurrencyRequestDto.currencyTo());
    validationService.validateCurrencies(
        updateCurrencyRequestDto.currencyFrom(), updateCurrencyRequestDto.currencyTo());
    validationService.validateAmount(updateCurrencyRequestDto.exchangeRateFromTo());
    validationService.validateAmount(updateCurrencyRequestDto.exchangeRateToFrom());
    validationService.validateAmount(updateCurrencyRequestDto.officialBankRate());
  }
}
