package com.tech.services.impl;

import com.tech.model.Person;
import com.tech.processor.CSVConverter;
import com.tech.processor.CSVConverterImpl;
import com.tech.services.CsvService;
import com.tech.services.FileStorageService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonServiceImpl<T> implements CsvService {

    private FileStorageService storageService;
    private CSVConverter<Person> converter;

    public PersonServiceImpl(FileStorageService storageService) {
        this.storageService = storageService;
        converter = new CSVConverterImpl<Person>(',', Person::new);
    }

    @Override
    public void processCsvFile(String fileName) {
        System.out.println("Process file called >>>>>>>>> ");
        // get the file
        Path path = storageService.getFilePath(fileName);
        try {
            Stream<Person> s = converter.processCsv(path);

            if(s != null) {
                List<Person> data = s.collect(Collectors.toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> String generateCsvFile(List<T> items) {
        return null;
    }
}
