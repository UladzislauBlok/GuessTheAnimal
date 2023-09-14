package org.animal.presentationLayer;

import org.animal.businessLogicLayer.GameService;
import org.animal.businessLogicLayer.TreeNode;
import org.animal.businessLogicLayer.exceptions.NotFoundAnimalException;
import org.animal.businessLogicLayer.requestService.FileRequestHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandlerSingleton;

import java.util.Scanner;

public class GameController {
    private final Scanner scanner;
    private final GameService gameService;
    private final AnimalController animalController;
    private final FactController factController;
    private final TreeController treeController;
    private final FileRequestHandler fileRequestHandler;

    public GameController() {
        this.scanner = ScannerSingleton.getScanner();
        this.gameService = new GameService();
        this.animalController = new AnimalController();
        this.factController = new FactController();
        this.treeController = new TreeController();
        this.fileRequestHandler = FileRequestHandlerSingleton.getSingleton();
    }

    public void startGame(TreeNode root) {
        System.out.println("Let's play a game!");
        System.out.println("You think of an animal, and I guess it.");
        System.out.println("Press enter when you're ready.");
        System.out.print(">");
        scanner.nextLine();

        boolean isWon = false;
        TreeNode nodeForChange = null;

        try {
            isWon = gameService.handleRoot(root);
        } catch (NotFoundAnimalException e) {
            nodeForChange = e.getTreeNode();
        }

        if (isWon)
            System.out.println("I won!");

        if (nodeForChange != null) {
            TreeNode newAnimalNode = animalController.gameEndAnimalInputProcess();
            TreeNode newNode = factController.getNewNode(nodeForChange, newAnimalNode);

            if (root == nodeForChange) {
                root.setLeft(newNode.getLeft());
                root.setRight(new TreeNode(newNode.getRight().getValue()));
                root.setValue(newNode.getValue());
            } else {
                treeController.addNode(root, newNode, nodeForChange);
            }
        }

        System.out.println("Do you like to play again?");
        System.out.print("> ");
        if (fileRequestHandler.getAndHandleAnswer())
            startGame(root);
    }
}
