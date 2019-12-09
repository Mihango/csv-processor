package com.tech.services;

import com.tech.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> processCsvFile(String fileName) throws Exception;

    String generateCsvFile(List<Person> personList);
}
