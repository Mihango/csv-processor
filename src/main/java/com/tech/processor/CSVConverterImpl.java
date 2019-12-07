package com.tech.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVConverterImpl<T> implements CSVConverter<T> {

    private Character separator;
    private Supplier<T> supplier;

    private CSVConverterImpl() {
    }

    public CSVConverterImpl(Character separator, Supplier<T> supplier) {
        this.separator = separator;
        this.supplier = supplier;
    }

    @Override
    public Path convertObjectsToCsv(List<T> objects) {
        return null;
    }

    @Override
    public Stream<T> processCsv(Path csvFile) throws Exception {
        System.out.println("convert file called >>>>>>>>> ");
        if (separator != null && csvFile.toFile().exists()) {
            // get headers
            String[] heads = getHeaders(csvFile);

            System.out.println("Check is null " + (heads == null));

            AtomicReference<String[]> headers = new AtomicReference<>(heads);

            // check header is not null

            try (Stream<String> stream = Files.lines(csvFile).parallel()) {
                // convert line to given object
                List<T> data = stream
                        .parallel()
                        .map(line -> {
                            try {
                                return convertLineToObject(line, headers.get());
                            } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());

                data.remove(0);

                System.out.println("List data >>>>> " + Arrays.toString(data.toArray()));
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
        return fitsLine.map(s -> s.split(separator.toString())).orElse(null);
    }

    private T convertLineToObject(String csvLine, String[] headers) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        String[] items = csvLine.split(separator.toString());
        return setFields(items, headers);
    }

    @SuppressWarnings("unchecked, rawTypes")
    private T setFields(String[] values, String[] headers) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clazz = supplier.get().getClass();
        T t = (T) clazz.getDeclaredConstructor().newInstance();

        for (int i = 0; i < headers.length; i++) {
            Field field = clazz.getDeclaredField(headers[i].toLowerCase());
            field.setAccessible(true);
            field.set(t, values[i]);
        }

        try {
            System.out.println("T class >>>>>>> " + new ObjectMapper().writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }
}
