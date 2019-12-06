package com.tech.processor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class CSVConverterImpl<T> implements CSVConverter<T> {

    private Character separator;

//    private String[] headers;

    @Override
    public Path convertObjectsToCsv(List<T> objects) {
        return null;
    }

    @Override
    public Stream<T> processCsv(Path csvFile) throws Exception {
        if (separator != null && csvFile.toFile().exists()) {
            // get headers
            AtomicReference<String[]> headers = new AtomicReference<>(getHeaders(csvFile));

            // check header is not null

            try (Stream<String> stream = Files.lines(csvFile).parallel()) {
                // convert line to given object
                return stream
                        .parallel()
                        .map(line -> convertLineToObject(line, headers.get()));
            } catch (IOException e) {
                throw new Exception("Error reading file");
            }
        }
        return Stream.empty();
    }

    /*
    @param csvFile

    generate names of items from the first line
     */
    private String[] getHeaders(Path csvFile) throws IOException {
        Optional<String> fitsLine = Files.lines(csvFile).findFirst();
        return fitsLine.map(s -> s.split(getSeparator().toString())).orElse(null);
    }

    private T convertLineToObject(String csvLine, String[] headers) {
        String[] items = csvLine.split(getSeparator().toString());
        return setFields(items, headers);
    }

    @SuppressWarnings("unchecked")
    private T setFields(String[] values, String[] headers) {
        for (String header : headers) {
            System.out.println("Header ------> " + header);
        }
        return (T) new Object();
    }
}
