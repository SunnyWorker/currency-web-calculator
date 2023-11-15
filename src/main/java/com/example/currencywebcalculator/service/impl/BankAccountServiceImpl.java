package com.example.currencywebcalculator.service.impl;

import com.example.currencywebcalculator.entity.BankAccountEntity;
import com.example.currencywebcalculator.entity.key.BankAccountKey;
import com.example.currencywebcalculator.repository.BankAccountRepository;
import com.example.currencywebcalculator.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;

  @Override
  public BankAccountEntity getBankAccount(Long userId, Long currencyId) {
    BankAccountKey bankAccountKey =
        BankAccountKey.builder().userId(userId).currencyId(currencyId).build();
    return bankAccountRepository.getReferenceById(bankAccountKey);
  }

  @Override
  public void saveBankAccount(BankAccountEntity bankAccountEntity) {
    bankAccountRepository.save(bankAccountEntity);
  }
}
