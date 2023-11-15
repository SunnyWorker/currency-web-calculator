package com.example.currencywebcalculator.controller;

import com.example.currencywebcalculator.dto.request.CreateTransactionRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(TransactionController.TRANSACTIONS_URL)
@RestController
@RequiredArgsConstructor
public class TransactionController {
  public static final String API_V1_URI = "/api/v1";
  public static final String TRANSACTIONS_URI = "/transactions";
  public static final String TRANSACTIONS_URL = API_V1_URI + TRANSACTIONS_URI;

  private final TransactionService transactionService;

  @PostMapping
  public MessageResponseDto createTransaction(
      CreateTransactionRequestDto createTransactionRequestDto) {
    return transactionService.createTransaction(createTransactionRequestDto);
  }
}
