package org.animal.presentationLayer;

import org.animal.businessLogicLayer.animalService.AnimalHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalController {

    private final AnimalHandler animalHandler;
    private final Scanner scanner;
    private static final List<String> animalList = new ArrayList<>();

    public AnimalController(Scanner scanner) {
        this.animalHandler = new AnimalHandler();
        this.scanner = scanner;
    }

    public void animalInputProcess() {
        System.out.println("Enter the first animal:");
        System.out.print("> ");
        animalList.add(animalHandler.handleAnimal(scanner.nextLine().toLowerCase()));
        System.out.println("Enter the second animal:");
        System.out.print("> ");
        animalList.add(animalHandler.handleAnimal(scanner.nextLine().toLowerCase()));
    }

    public static List<String> getAnimalList() {
        return animalList;
    }
}
