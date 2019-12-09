package com.tech.reources;

import com.tech.services.PersonService;
import com.tech.services.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvFileTestResource {

    private PersonService personService;
    private FileStorageService fileStorageService;

    public CsvFileTestResource(PersonService personService, FileStorageService fileStorageService) {
        this.personService = personService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    private ResponseEntity uploadFile(MultipartFile file) throws Exception {
        return ResponseEntity.ok(personService.processCsvFile(fileStorageService.storeFile(file)));
    }
}
