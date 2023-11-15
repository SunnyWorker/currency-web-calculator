package com.example.currencywebcalculator.entity.key;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BankAccountKey implements Serializable {
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "currency_id")
  private Long currencyId;
}
