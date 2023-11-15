package com.example.currencywebcalculator.entity;

import com.example.currencywebcalculator.entity.listener.CreateEntityListener;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(CreateEntityListener.class)
public abstract class CreatableEntity {
  @Column(name = "created_at", nullable = false, updatable = false)
  private ZonedDateTime createAt;
}
