package com.tech.services.impl;

import com.tech.model.Person;
import com.tech.processor.CSVConverter;
import com.tech.processor.CSVConverterImpl;
import com.tech.services.FileStorageService;
import com.tech.services.PersonService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private FileStorageService storageService;
    private CSVConverter<Person> converter;

    public PersonServiceImpl(FileStorageService storageService) {
        this.storageService = storageService;
        converter = new CSVConverterImpl<>(',', Person::new);
    }

    @Override
    public List<Person> processCsvFile(String fileName) throws Exception {
        System.out.println("Process file called >>>>>>>>> ");
        // get the file
        Path path = storageService.getFilePath(fileName);
        try {
            List<Person> s = converter.processCsv(path);

            if(!s.isEmpty()) {
                // save data in database
            }

            return s;
        } catch (Exception e) {
            throw new Exception("Error processing file");
        }
    }

    @Override
    public String generateCsvFile(List<Person> personList) {
        return null;
    }
}
