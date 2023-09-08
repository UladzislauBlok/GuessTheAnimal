package org.animal.businessLogicLayer.requestService;

import org.animal.persistenceLayer.fileIO.Reader;

import java.util.List;

public class FileRequestHandler {

    private final List<String> positiveAnswers;
    private final List<String> negativeAnswers;

    FileRequestHandler() {
        positiveAnswers = Reader.readFile("C:\\JavaDev\\Guess the Animal (Java)\\Guess the Animal (Java)\\task\\src\\resources\\positiveAnswerOptions.txt");
        negativeAnswers = Reader.readFile("C:\\JavaDev\\Guess the Animal (Java)\\Guess the Animal (Java)\\task\\src\\resources\\negativeAnswerOptions.txt");
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
