package org.animal.businessLogicLayer;

import org.animal.businessLogicLayer.exceptions.NotFoundAnimalException;
import org.animal.businessLogicLayer.requestService.FileRequestHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandlerSingleton;

public class GameService {
    private final FileRequestHandler fileRequestHandler;

    public GameService() {
        this.fileRequestHandler = FileRequestHandlerSingleton.getSingleton();
    }

    public boolean handleRoot(TreeNode root) {
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.println("Is it " + root.getValue() + "?");
            System.out.print("> ");
            if (fileRequestHandler.getAndHandleAnswer())
                return true;

            throw new NotFoundAnimalException(root);
        } else {
            System.out.println(root.getValue());
            System.out.print("> ");
            boolean isTrue = fileRequestHandler.getAndHandleAnswer();

            return isTrue ? handleRoot(root.getRight()) : handleRoot(root.getLeft());
        }
    }
}
