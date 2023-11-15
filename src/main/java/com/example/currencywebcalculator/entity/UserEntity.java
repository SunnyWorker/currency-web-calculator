package com.example.currencywebcalculator.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
  @Id
  @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String first_name;

  @Column(name = "last_name", nullable = false)
  private String last_name;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birth_date;
}
