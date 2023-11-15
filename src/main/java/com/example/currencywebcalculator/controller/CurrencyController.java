package com.example.currencywebcalculator.controller;

import com.example.currencywebcalculator.dto.request.UpdateCurrencyRequestDto;
import com.example.currencywebcalculator.dto.response.CurrencyNameResponseDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.service.CurrencyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(CurrencyController.CURRENCY_URL)
@RestController
@RequiredArgsConstructor
public class CurrencyController {

  public static final String API_V1_URI = "/api/v1";
  public static final String CURRENCY_URI = "/currencies";
  public static final String CURRENCY_URL = API_V1_URI + CURRENCY_URI;

  private final CurrencyService currencyService;

  @GetMapping
  public List<CurrencyNameResponseDto> getAllCurrencies() {
    return currencyService.getAllCurrencies();
  }

  @PostMapping
  public MessageResponseDto updateCurrencies(
      List<UpdateCurrencyRequestDto> updateCurrencyRequestDtos) {
    return currencyService.updateCurrencies(updateCurrencyRequestDtos);
  }
}
