package org.animal.RequestHandlers;

import org.animal.ReadUtil.Reader;

import java.util.List;

public class RequestHandler {

    private final List<String> positiveAnswers;
    private final List<String> negativeAnswers;
    private final List<String> vowelLetters = List.of("a", "e", "i", "o", "u");

    RequestHandler() {
        positiveAnswers = Reader.readFile("src/main/resources/positiveResponseOptions.txt");
        negativeAnswers = Reader.readFile("src/main/resources/negativeAnswerOptions.txt");
    }

    public String handleAnimal(String animal) {
        String[] strs = animal.split("\\s+");

        if (strs[0].equals("the")) {
            char firstC = strs[1].charAt(0);
            if (vowelLetters.contains(firstC + "")) {
                strs[0] = "an";
            } else {
                strs[0] = "a";
            }

            return String.join(" ", strs);
        }

        if (!strs[0].equals("a") && !strs[0].equals("an")) {
            char firstC = animal.charAt(0);
            if (vowelLetters.contains(firstC + "")) {
                return "an " + animal;
            } else {
                return "a " + animal;
            }
        }

        return animal;
    }

    public State handleAnswer(String answer) {
        char lastC = answer.charAt(answer.length() - 1);
        if (lastC == '.' || lastC == '!')
            answer = answer.substring(0, answer.length() - 1);
        answer = answer.toLowerCase().trim();

        if (positiveAnswers.contains(answer))
            return State.TRUE;

        if (negativeAnswers.contains(answer))
            return State.FALSE;

        return State.UNKNOWN_ANSWER;
    }
}
