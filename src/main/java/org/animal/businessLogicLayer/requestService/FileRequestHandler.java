package org.animal.businessLogicLayer.requestService;

import org.animal.businessLogicLayer.responseSevrice.FileResponse;
import org.animal.businessLogicLayer.responseSevrice.FileResponseSingleton;
import org.animal.persistenceLayer.fileIO.Reader;
import org.animal.presentationLayer.ScannerSingleton;

import java.util.List;
import java.util.Scanner;

public class FileRequestHandler {

    private final List<String> positiveAnswers;
    private final List<String> negativeAnswers;
    private final Scanner scanner;
    private final FileResponse fileResponse;

    FileRequestHandler() {
        positiveAnswers = Reader.readFile("C:\\JavaDev\\Guess the Animal (Java)\\Guess the Animal (Java)\\task\\src\\resources\\positiveAnswerOptions.txt");
        negativeAnswers = Reader.readFile("C:\\JavaDev\\Guess the Animal (Java)\\Guess the Animal (Java)\\task\\src\\resources\\negativeAnswerOptions.txt");
        this.scanner = ScannerSingleton.getScanner();
        this.fileResponse = FileResponseSingleton.getSingleton();
    }

    private State handleAnswer(String answer) {
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

    public boolean getAndHandleAnswer() {
        String answer = scanner.nextLine().toLowerCase();
        State state = handleAnswer(answer);

        while (state.equals(State.UNKNOWN_ANSWER)) {
            System.out.println(fileResponse.getClarification());
            System.out.print("> ");
            state = handleAnswer(scanner.nextLine());
        }

        return state.equals(State.TRUE);
    }
}
