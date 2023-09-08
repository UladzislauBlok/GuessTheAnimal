package org.animal.persistenceLayer.fileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(path)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException();
        }

        return list;
    }
}
