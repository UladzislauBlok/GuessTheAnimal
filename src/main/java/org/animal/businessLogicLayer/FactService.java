package org.animal.businessLogicLayer;

import org.animal.businessLogicLayer.requestService.FileRequestHandler;
import org.animal.businessLogicLayer.requestService.FileRequestHandlerSingleton;

import java.util.Arrays;

public class FactService {

    private final FileRequestHandler fileRequestHandler;

    public FactService() {
        this.fileRequestHandler = FileRequestHandlerSingleton.getSingleton();
    }

    public String handleFact(String fact) {
        char c = fact.charAt(fact.length() - 1);
        if (c == '.' || c == '?' || c == '!')
            fact = fact.substring(0, fact.length() - 1);
        String[] strs = fact.split("\\s+");
        if (strs.length < 3)
            return null;

        if (!strs[0].equals("it") || !strs[1].equals("can") && !strs[1].equals("has") && !strs[1].equals("is"))
            return null;

        return String.join(" ", strs);
    }

    public boolean learnFact(String animal1, String animal2, String fact) {
        String[] strs = fact.split("\\s+");
        String restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
        String positiveFact;
        String negativeFact;
        switch (strs[1]) {
            case "can" -> {
                positiveFact = " can " ;
                negativeFact = " can't ";
            }
            case "has" -> {
                positiveFact = " has ";
                negativeFact = " doesn't have ";
            }
            case "is" -> {
                positiveFact = " is ";
                negativeFact = " isn't ";
            }
            default -> {
                positiveFact = "";
                negativeFact = "";
            }
        }

        boolean answer = fileRequestHandler.getAndHandleAnswer();
        System.out.println("I learned the following facts about animals:");

        if (answer) {
            System.out.println(" - The " + animal1.split("\\s")[1] + negativeFact + restFact + ".");
            System.out.println(" - The " + animal2.split("\\s")[1] + positiveFact + restFact + ".");
        } else {
            System.out.println(" - The " + animal1.split("\\s")[1] + positiveFact + restFact + ".");
            System.out.println(" - The " + animal2.split("\\s")[1] + negativeFact + restFact + ".");
        }

        return answer;
    }

    public TreeNode learnQuestion(String fact, boolean isNode1Left, TreeNode node1, TreeNode node2) {
        String[] strs = fact.split("\\s+");
        String restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
        String verb;
        switch (strs[1]) {
            case "can" -> verb = "Can it ";
            case "has" -> verb = "Does it have ";
            case "is" -> verb = "Is it ";
            default -> verb = "";
        }
        String question = verb + restFact + "?";
        System.out.println(" - " + question);
        System.out.println("Nice! I've learned so much about animals!\n");
        return isNode1Left ? new TreeNode(question, node1, node2) : new TreeNode(question, node2, node1);
    }
}
