package com.example.currencywebcalculator.service;

import com.example.currencywebcalculator.dto.response.RateUpdateDto;

public interface RateUpdateService {
  RateUpdateDto getLatestRateUpdate(String currencyFrom, String currencyTo);
}
