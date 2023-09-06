package org.animal.ResponseUtil;

import org.animal.ReadUtil.Reader;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Response {
    private final Random random;
    private final List<String> clarificationQuestions;
    private final List<String> farewellOptions;

    Response() {
        this.random = new Random();
        this.clarificationQuestions = Reader.readFile("src/main/resources/clarificationQuestions.txt");
        this.farewellOptions = Reader.readFile("src/main/resources/farewellOptions.txt");
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
}
