package com.spring.poc.controller;

import com.spring.poc.service.FileService;
import java.io.ByteArrayOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FileController {
  @Autowired FileService fileService;

  @GetMapping("/download")
  public ResponseEntity download(@RequestParam String fileName) {
    ByteArrayOutputStream stream = null;
    try {
      stream = fileService.downloadCsv();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    if (stream == null) {
      return ResponseEntity.internalServerError().body("unable to retrieve data");
    }
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("text/csv"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .body(stream.toByteArray());
  }
}
