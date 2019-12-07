package com.tech.processor;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface CSVConverter<T> {
    Path convertObjectsToCsv(List<T> objects);
    Stream<T> processCsv(Path csvFile)  throws Exception;
}
