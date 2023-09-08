package org.animal.businessLogicLayer.factService;

import org.animal.businessLogicLayer.requestService.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactHandler {

    public String handleFact(String fact) {
        if (fact.charAt(fact.length() - 1) == '.' || fact.charAt(fact.length() - 1) == '?' || fact.charAt(fact.length() - 1) == '!')
            fact = fact.substring(0, fact.length() - 1);
        String[] strs = fact.split("\\s+");
        if (strs.length < 3)
            return null;

        if (!strs[0].equals("it") || !strs[1].equals("can") && !strs[1].equals("has") && !strs[1].equals("is"))
            return null;

        return String.join(" ", strs);
    }

    public List<String> learnFact(State state, List<String> animalList, String fact) {
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
        List<String> factList = new ArrayList<>();

        if (state.equals(State.FALSE)) {
            factList.add("The " + animalList.get(0).split("\\s")[1] + positiveFact + restFact + ".");
            factList.add("The " + animalList.get(1).split("\\s")[1] + negativeFact + restFact + ".");
        } else {
            factList.add("The " + animalList.get(0).split("\\s")[1] + negativeFact + restFact + ".");
            factList.add("The " + animalList.get(1).split("\\s")[1] + positiveFact + restFact + ".");
        }

        return factList;
    }

    public String learnQuestion(String fact) {
        String[] strs = fact.split("\\s+");
        String restFact = String.join(" ", Arrays.copyOfRange(strs, 2, strs.length));
        String verb;
        switch (strs[1]) {
            case "can" -> verb = "Can it ";
            case "has" -> verb = "Does it have ";
            case "is" -> verb = "Is it ";
            default -> verb = "";
        }
        return verb + restFact + "?";
    }
}
