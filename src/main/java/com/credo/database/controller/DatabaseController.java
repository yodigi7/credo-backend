package com.credo.database.controller;

import com.credo.database.dto.PersonDto;
import com.credo.database.service.DatabaseService;
import com.credo.database.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;
    @Autowired
    ExcelService excelService;

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonDto person) {
        // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(databaseService.createPerson(person));
    }

    @GetMapping
    public ResponseEntity<Resource> exportToExcel() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (outputStream) {
            excelService.getDbToWorkbook().write(outputStream);
        } catch (IOException e) {
            log.error("Unable to export to excel.", e);
        }
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
