package com.tech.processor;

import java.nio.file.Path;
import java.util.List;

public interface CSVConverter<T> {
    Path convertObjectsToCsv(List<T> objects);

    List<T> processCsv(Path csvFile)  throws Exception;
}
