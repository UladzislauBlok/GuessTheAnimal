package org.animal.businessLogicLayer.animalService;

import java.util.List;

public class AnimalHandler {
    private final List<String> vowelLetters = List.of("a", "e", "i", "o", "u");

    public String handleAnimal(String animal) {
        String[] strs = animal.split("\\s+");

        if (strs[0].equals("the")) {
            char firstC = strs[1].charAt(0);
            if (vowelLetters.contains(firstC + "")) {
                strs[0] = "an";
            } else {
                strs[0] = "a";
            }

            return String.join(" ", strs);
        }

        if (!strs[0].equals("a") && !strs[0].equals("an")) {
            char firstC = animal.charAt(0);
            if (vowelLetters.contains(firstC + "")) {
                return "an " + animal;
            } else {
                return "a " + animal;
            }
        }

        return animal;
    }
}
