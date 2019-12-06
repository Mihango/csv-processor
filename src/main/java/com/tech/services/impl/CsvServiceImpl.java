package com.tech.services.impl;

import com.tech.services.CsvService;
import com.tech.services.FileStorageService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {

    private FileStorageService storageService;

    public CsvServiceImpl(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void processCsvFile(String fileName) {
        // get the file
        Path path = storageService.getFilePath(fileName);
    }

    @Override
    public <T> String generateCsvFile(List<T> items) {
        return null;
    }
}
