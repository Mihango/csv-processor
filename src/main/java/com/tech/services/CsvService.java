package com.tech.services;

import java.util.List;

public interface CsvService {
    void processCsvFile(String fileName);

    <T> String generateCsvFile(List<T> items);
}
