package com.example.currencywebcalculator.service;

import com.example.currencywebcalculator.dto.request.CreateTransactionRequestDto;
import com.example.currencywebcalculator.dto.response.MessageResponseDto;

public interface TransactionService {
  MessageResponseDto createTransaction(CreateTransactionRequestDto createTransactionRequestDto);
}
