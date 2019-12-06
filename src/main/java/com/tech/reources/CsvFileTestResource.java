package com.tech.reources;

import com.tech.services.CsvService;
import com.tech.services.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvFileTestResource {

    private CsvService csvService;
    private FileStorageService fileStorageService;

    public CsvFileTestResource(CsvService csvService, FileStorageService fileStorageService) {
        this.csvService = csvService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    private ResponseEntity uploadFile(MultipartFile file) {
        csvService.processCsvFile(fileStorageService.storeFile(file));
        return ResponseEntity.ok().build();
    }
}
