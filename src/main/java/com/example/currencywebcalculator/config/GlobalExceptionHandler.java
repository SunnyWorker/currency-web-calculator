package com.example.currencywebcalculator.config;

import com.example.currencywebcalculator.dto.response.MessageResponseDto;
import com.example.currencywebcalculator.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<MessageResponseDto> handleBusinessException(final BusinessException ex) {
    return ResponseEntity.status(ex.getHttpStatus()).body(new MessageResponseDto(ex.getMessage()));
  }
}
