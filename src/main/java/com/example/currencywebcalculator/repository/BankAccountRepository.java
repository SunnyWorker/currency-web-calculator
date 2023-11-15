package com.example.currencywebcalculator.repository;

import com.example.currencywebcalculator.entity.BankAccountEntity;
import com.example.currencywebcalculator.entity.key.BankAccountKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, BankAccountKey> {}
