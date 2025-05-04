package com.spring.poc.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.*;
import com.spring.poc.model.Person;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileService {
  private final String[] HEADER = new String[] {"First Name", "Last Name", "Date of Birth"};

  public ByteArrayOutputStream downloadCsv() throws IOException, CsvException {
    List<Person> people = peopleMock();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try (OutputStreamWriter streamWriter =
            new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(streamWriter)) {
      csvWriter.writeNext(HEADER);
      StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(csvWriter).build();
      beanToCsv.write(people);
    }
    return outputStream;
  }

  List<Person> peopleMock() {
    return Arrays.asList(
        new Person("Mike", "Czech", LocalDate.of(1990, 12, 1)),
        new Person("Josef", "K", LocalDate.of(1925, 4, 26)),
        new Person("Gregory", "Illinivich", LocalDate.of(2009, 2, 24)));
  }
}
