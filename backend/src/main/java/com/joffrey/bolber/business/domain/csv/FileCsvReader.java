package com.joffrey.bolber.business.domain.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class FileCsvReader implements CsvReader {

    public FileCsvReader() {

    }

    @Override
    public String[][] readFile(final String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filePath))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException ignored) {
          
        }


        return records.stream()
                .map(lst -> lst.toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
