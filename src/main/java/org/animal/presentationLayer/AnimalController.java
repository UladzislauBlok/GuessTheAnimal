package org.animal.presentationLayer;

import org.animal.businessLogicLayer.AnimalService;
import org.animal.businessLogicLayer.TreeNode;

import java.util.Scanner;

public class AnimalController {

    private final AnimalService animalHandler;
    private final Scanner scanner;

    public AnimalController() {
        this.animalHandler = new AnimalService();
        this.scanner = ScannerSingleton.getScanner();
    }

    public TreeNode firstAnimalInputProcess() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");
        System.out.print("> ");
        String animalName = scanner.nextLine().toLowerCase();
        return new TreeNode(animalHandler.handleAnimal(animalName));
    }

    public TreeNode gameEndAnimalInputProcess() {
        System.out.println("I give up. What animal do you have in mind?");
        System.out.print("> ");
        String animalName = scanner.nextLine().toLowerCase();
        return new TreeNode(animalHandler.handleAnimal(animalName));
    }
}
