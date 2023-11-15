package com.example.currencywebcalculator.mapper;

import com.example.currencywebcalculator.dto.response.CurrencyNameResponseDto;
import com.example.currencywebcalculator.entity.CurrencyEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
  List<CurrencyNameResponseDto> toListOfNames(List<CurrencyEntity> currencyEntities);
}
