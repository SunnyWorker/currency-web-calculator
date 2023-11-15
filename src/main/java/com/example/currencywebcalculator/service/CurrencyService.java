package com.example.currencywebcalculator.service;

import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.CurrencyNameResponseDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import java.util.List;

public interface CurrencyService {
  List<CurrencyNameResponseDto> getAllCurrencies();

  MessageResponseDto updateCurrencies(List<UpdateCurrencyRequestDto> updateCurrencyRequestDtos);
}
