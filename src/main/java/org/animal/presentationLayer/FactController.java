package org.animal.presentationLayer;

import org.animal.businessLogicLayer.factService.FactHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandlerSingleton;
import org.animal.businessLogicLayer.requestService.State;
import org.animal.businessLogicLayer.responseSevrice.FileResponse;
import org.animal.businessLogicLayer.responseSevrice.FileResponseSingleton;

import java.util.List;
import java.util.Scanner;

public class FactController {

    private List<String> animalList;
    private final Scanner scanner;
    private final FileRequestHandler fileRequestHandler;
    private final FileResponse fileResponse;
    private final FactHandler factHandler;

    public FactController(Scanner scanner) {
        this.scanner = scanner;
        animalList = AnimalController.getAnimalList();
        this.fileRequestHandler = FileRequestHandlerSingleton.getSingleton();
        this.fileResponse = FileResponseSingleton.getSingleton();
        this.factHandler = new FactHandler();
    }
    public void factInputProcess() {
        if (animalList.size() < 2) {
            System.out.println("First, enter the org.animal");
            return;
        }

        System.out.println("Specify a fact that distinguishes " +  animalList.get(0) + " from " + animalList.get(1) + ".");
        System.out.println("The sentence should be of the format: 'It can/has/is ...'.\n");
        System.out.print("> ");

        String fact = factHandler.handleFact(scanner.nextLine().toLowerCase());
        while (fact == null) {
            System.out.println("The examples of a statement:");
            System.out.println(" - It can fly");
            System.out.println(" - It has horn");
            System.out.println(" - It has horn");
            System.out.println("Specify a fact that distinguishes " +  animalList.get(0) + " from " + animalList.get(1));
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.\n");
            System.out.print("> ");
            fact = factHandler.handleFact(scanner.nextLine().toLowerCase());
        }

        System.out.println("Is it correct for " + animalList.get(1) + "?");
        System.out.print("> ");
        State state = fileRequestHandler.handleAnswer(scanner.nextLine());

        while (state.equals(State.UNKNOWN_ANSWER)) {
            System.out.println(fileResponse.getClarification());
            System.out.print("> ");
            state = fileRequestHandler.handleAnswer(scanner.nextLine());
        }

        System.out.println("I learned the following facts about org.animal:");
        List<String> factList = factHandler.learnFact(state, animalList, fact);
        System.out.println(" - " + factList.get(0));
        System.out.println(" - " + factList.get(1));
        System.out.println("I can distinguish these org.animal by asking the question:");
        String question = factHandler.learnQuestion(fact);
        System.out.println(" - " + question + "\n");
    }

    public void refreshAnimalList() {
        animalList = AnimalController.getAnimalList();
    }
}
