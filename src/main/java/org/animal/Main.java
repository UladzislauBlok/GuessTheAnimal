package org.animal;

import org.animal.RequestHandlers.RequestHandler;
import org.animal.RequestHandlers.RequestHandlerSingleton;
import org.animal.RequestHandlers.State;
import org.animal.ResponseUtil.Response;
import org.animal.ResponseUtil.ResponseSingleton;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Response response = ResponseSingleton.getSingleton();
        RequestHandler requestHandler = RequestHandlerSingleton.getSingleton();
        Scanner scanner = new Scanner(System.in);

        System.out.println(response.getGreeting() + "\n");
        System.out.println("Enter an animal:");
        System.out.print("> ");
        String animal = requestHandler.handleAnimal(scanner.nextLine().toLowerCase());
        System.out.println("Is it " + animal + "?");
        System.out.print("> ");
        State state = requestHandler.handleAnswer(scanner.nextLine());

        while (state.equals(State.UNKNOWN_ANSWER)) {
            System.out.println(response.getClarification());
            System.out.print("> ");
            state = requestHandler.handleAnswer(scanner.nextLine());
        }

        System.out.println("You answered: " + (state.equals(State.TRUE) ? "Yes" : "No") + "\n");

        System.out.println(response.getFarewell());
    }
}
