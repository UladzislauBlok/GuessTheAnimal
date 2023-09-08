package org.animal.presentationLayer;

import org.animal.businessLogicLayer.responseSevrice.FileResponse;
import org.animal.businessLogicLayer.responseSevrice.FileResponseSingleton;

import java.util.Scanner;

public class Controller {
    private final FileResponse response;
    private final Scanner scanner;
    private final AnimalController animalController;
    private final FactController factController;

    public Controller() {
        this.response = FileResponseSingleton.getSingleton();
        this.scanner = new Scanner(System.in);
        this.animalController = new AnimalController(scanner);
        this.factController = new FactController(scanner);
    }

    public void start() {
        System.out.println(response.getGreeting() + "\n");

        animalController.animalInputProcess();
        factController.factInputProcess();

        System.out.println(response.getFarewell());
    }
}
