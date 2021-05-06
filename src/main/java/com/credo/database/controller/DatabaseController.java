package com.credo.database.controller;

import com.credo.database.dto.PersonDto;
import com.credo.database.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonDto person) {
        // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(databaseService.createPerson(person));
    }
}
