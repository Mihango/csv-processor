package com.tech.processor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class CSVConverterImpl<T> implements CSVConverter<T> {

    private Character separator;

    @Override
    public Path convertObjectsToCsv(List<T> objects) {
        return null;
    }

    @Override
    public List<T> processCsv(Path csvFile) throws Exception {
        if (separator != null) {
            // process file
            try (Stream<String> stream = Files.lines(csvFile).parallel()) {
                // convert line to given object
                List<T> items = stream
                        .parallel()
                        .map(this::convertLineToObject)
                        .collect(Collectors.toList());

            } catch (IOException e) {
                throw new Exception("Error reading file");
            }
        }
        return null;
    }

    private T convertLineToObject(String csvLine) {
        String[] items = csvLine.split(getSeparator().toString());
        return setFields(items);
    }

    private T setFields(String[] values) {
        return (T) new Object();
    }
}
