package com.example.currencywebcalculator.entity.listener;

import com.example.currencywebcalculator.config.TimeProvider;
import com.example.currencywebcalculator.entity.RateUpdateEntity;
import javax.persistence.PrePersist;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateEntityListener {
  @Autowired private TimeProvider timeProvider;

  @PrePersist
  public void prePersist(final RateUpdateEntity entity) {
    entity.setCreateAt(timeProvider.getCurrentZonedDateTime());
  }
}
