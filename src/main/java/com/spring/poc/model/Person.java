package com.spring.poc.model;

import com.opencsv.bean.CsvBindByPosition;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  @CsvBindByPosition(position = 0)
  private String firstName;

  @CsvBindByPosition(position = 1)
  private String lastName;

  @CsvBindByPosition(position = 2)
  private LocalDate dob;
}
