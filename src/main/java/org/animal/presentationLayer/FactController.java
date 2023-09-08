package org.animal.presentationLayer;

import org.animal.businessLogicLayer.FactService;
import org.animal.businessLogicLayer.TreeNode;

import java.util.Scanner;

public class FactController {
    private final Scanner scanner;
    private final FactService factHandler;

    public FactController() {
        this.scanner = ScannerSingleton.getScanner();
        this.factHandler = new FactService();
    }
    public TreeNode getNewNode(TreeNode node1, TreeNode node2) {
        String animal1 = node1.getValue();
        String animal2 = node2.getValue();
        System.out.println("Specify a fact that distinguishes " +  animal1 + " from " + animal2 + ".");
        System.out.println("""
                The sentence should satisfy one of the following templates:
                - It can ...
                - It has ...
                - It is a/an ...
                """);
        System.out.print("> ");

        String fact = factHandler.handleFact(scanner.nextLine().toLowerCase());
        while (fact == null) {
            System.out.println("The examples of a statement:");
            System.out.println(" - It can fly");
            System.out.println(" - It has horn");
            System.out.println(" - It has horn");
            System.out.println("Specify a fact that distinguishes " +  animal1 + " from " + animal2);
            System.out.println("""
                The sentence should satisfy one of the following templates:
                - It can ...
                - It has ...
                - It is a/an ...
                """);
            System.out.print("> ");
            fact = factHandler.handleFact(scanner.nextLine().toLowerCase());
        }

        System.out.println("Is the statement correct for " + animal2 + "?");
        System.out.print("> ");

        boolean isNode1Left = factHandler.learnFact(animal1, animal2, fact);
        System.out.println("I can distinguish these animals by asking the question:");
        return factHandler.learnQuestion(fact, isNode1Left, node1, node2);
    }
}
