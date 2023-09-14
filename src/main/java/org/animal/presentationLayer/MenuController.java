package org.animal.presentationLayer;

import org.animal.businessLogicLayer.TreeNode;

import java.util.Scanner;

public class MenuController {
    private final Scanner scanner;
    private final GameController gameController;
    private final TreeController treeController;

    public MenuController() {
        this.scanner = ScannerSingleton.getScanner();
        gameController = new GameController();
        this.treeController = new TreeController();
    }

    public void printMenu() {
        System.out.print("""
                What do you want to do:

                1. Play the guessing game
                2. List of all animals
                3. Search for an animal
                4. Calculate statistics
                5. Print the Knowledge Tree
                0. Exit
                >\s""");
    }

    public boolean processChoice(TreeNode root) {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> gameController.startGame(root);
            case "2" -> treeController.printAnimalList(root);
            case "3" -> treeController.printAnimalPath(root);
            case "4" -> treeController.printStats(root);
            case "5" -> treeController.printTree(root);
            case "0" -> {
                return true;
            }
            case "default" -> System.out.println("Unknown operation");
        }
        return false;
    }
}
