package com.example.currencywebcalculator.service.impl;

import com.example.currencywebcalculator.dto.response.RateUpdateDto;
import com.example.currencywebcalculator.exception.BusinessException;
import com.example.currencywebcalculator.mapper.RateUpdateMapper;
import com.example.currencywebcalculator.repository.RateUpdateRepository;
import com.example.currencywebcalculator.service.RateUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateUpdateServiceImpl implements RateUpdateService {

  private final RateUpdateRepository rateUpdateRepository;
  private final RateUpdateMapper mapper;

  @Override
  public RateUpdateDto getLatestRateUpdate(String currencyFrom, String currencyTo) {
    return mapper.toDto(
        rateUpdateRepository
            .findLatestRateUpdate(currencyFrom, currencyTo)
            .orElseThrow(
                () -> {
                  throw new BusinessException(
                      HttpStatus.NOT_FOUND,
                      BusinessException.NO_RATE_UPDATE_FOR_CURRENCIES.formatted(
                          currencyFrom, currencyTo));
                }));
  }
}
