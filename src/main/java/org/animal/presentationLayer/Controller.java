package org.animal.presentationLayer;

import org.animal.businessLogicLayer.TreeNode;
import org.animal.businessLogicLayer.responseSevrice.FileResponse;
import org.animal.businessLogicLayer.responseSevrice.FileResponseSingleton;
import org.animal.persistenceLayer.fileIO.JacksonDataBind;

public class Controller {
    private final FileResponse response;
    private final AnimalController animalController;
    private final MenuController menuController;

    public Controller() {
        this.response = FileResponseSingleton.getSingleton();
        this.animalController = new AnimalController();
        this.menuController = new MenuController();
    }

    public void start() {
        System.out.println(response.getGreeting() + "\n");
        String path = "src/main/resources/animals.json";
        TreeNode root;

        root = JacksonDataBind.fromJson(path);

        if (root == null || root.getValue() == null)
            root = animalController.firstAnimalInputProcess();

        System.out.println("Welcome to the animal expert system!\n");

        boolean isFinish = false;
        while (!isFinish) {
            menuController.printMenu();
            isFinish = menuController.processChoice(root);
        }

        JacksonDataBind.toJson(root, path);

        System.out.println("\n" + response.getFarewell());
    }
}
