package org.animal.presentationLayer;

import org.animal.businessLogicLayer.GameService;
import org.animal.businessLogicLayer.TreeNode;

import java.util.Scanner;

public class GameController {
    private final Scanner scanner;
    private final GameService gameService;

    public GameController() {
        this.scanner = ScannerSingleton.getScanner();
        this.gameService = new GameService();
    }

    public boolean startGame(TreeNode root) {
        System.out.println("Let's play a game!");
        System.out.println("You think of an animal, and I guess it.");
        System.out.println("Press enter when you're ready.");
        System.out.print(">");
        scanner.nextLine();
        return gameService.handleRoot(root);
    }
}
