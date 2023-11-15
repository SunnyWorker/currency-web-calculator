package com.example.currencywebcalculator.mapper;

import com.example.currencywebcalculator.dto.response.RateUpdateDto;
import com.example.currencywebcalculator.entity.RateUpdateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateUpdateMapper {
  RateUpdateDto toDto(RateUpdateEntity rateUpdateEntity);
}
