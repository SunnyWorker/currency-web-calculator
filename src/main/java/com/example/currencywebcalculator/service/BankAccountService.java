package com.example.currencywebcalculator.service;

import com.example.currencywebcalculator.entity.BankAccountEntity;

public interface BankAccountService {
  BankAccountEntity getBankAccount(Long userId, Long currencyId);

  void saveBankAccount(BankAccountEntity bankAccountEntity);
}
