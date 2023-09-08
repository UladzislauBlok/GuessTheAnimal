package org.animal.presentationLayer;

import org.animal.businessLogicLayer.TreeNode;
import org.animal.businessLogicLayer.TreeService;
import org.animal.businessLogicLayer.requestService.FileRequestHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandlerSingleton;
import org.animal.businessLogicLayer.responseSevrice.FileResponse;
import org.animal.businessLogicLayer.responseSevrice.FileResponseSingleton;
import org.animal.businessLogicLayer.exceptions.NotFoundAnimalException;
import org.animal.persistenceLayer.fileIO.JacksonDataBind;

public class Controller {
    private final FileResponse response;
    private final AnimalController animalController;
    private final FactController factController;
    private final GameController gameController;
    private final TreeService treeService;
    private final FileRequestHandler fileRequestHandler;

    public Controller() {
        this.response = FileResponseSingleton.getSingleton();
        this.animalController = new AnimalController();
        this.factController = new FactController();
        this.gameController = new GameController();
        this.treeService = new TreeService();
        this.fileRequestHandler = FileRequestHandlerSingleton.getSingleton();
    }

    public void start() {
        System.out.println(response.getGreeting() + "\n");

        TreeNode root = JacksonDataBind.fromJson("src/main/resources/TreeNode.json");

        if (root.getValue() == null)
            root = animalController.firstAnimalInputProcess();

        boolean isPlay = true;
        while (isPlay) {
            TreeNode nodeForChange = null;
            boolean isWon = false;

            try {
                isWon = gameController.startGame(root);
            } catch (NotFoundAnimalException e) {
                nodeForChange = e.getTreeNode();
            }

            if (isWon)
                System.out.println("I won!");

            if (nodeForChange != null) {
                TreeNode newAnimalNode = animalController.gameEndAnimalInputProcess();
                TreeNode newNode = factController.getNewNode(nodeForChange, newAnimalNode);
                if (root == nodeForChange) {
                    root = newNode;
                } else {
                    treeService.addNode(root, newNode, nodeForChange);
                }
            }

            System.out.println("Would you like to play again?");
            System.out.print("> ");
            isPlay = fileRequestHandler.getAndHandleAnswer();
        }

        JacksonDataBind.toJson(root, "src/main/resources/TreeNode.json");
        System.out.println("\n" + response.getFarewell());
    }
}
