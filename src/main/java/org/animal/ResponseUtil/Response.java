package org.animal.ResponseUtil;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Response {
    private final Random random;
    private final List<String> clarificationQuestions;
    private final List<String> farewellOptions;

    Response() {
        this.random = new Random();
        this.clarificationQuestions = readFile("src/main/resources/clarificationQuestions.txt");
        this.farewellOptions = readFile("src/main/resources/farewellOptions.txt");
    }

    public String getClarification() {
        int index = random.nextInt(clarificationQuestions.size());
        return clarificationQuestions.get(index);
    }

    public String getFarewell() {
        int index = random.nextInt(farewellOptions.size());
        return farewellOptions.get(index);
    }

    public String getGreeting() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(LocalTime.of(5, 0)) && currentTime.isBefore(LocalTime.NOON)) {
            return "Good morning";
        } else if (currentTime.isAfter(LocalTime.NOON) && currentTime.isBefore(LocalTime.of(18,0))) {
            return "Good afternoon";
        } else {
            return "Good evening";
        }
    }

    private List<String> readFile(String path) {
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
