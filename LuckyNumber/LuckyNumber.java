/**
 * LuckyNumber
 * Assignment 1 - PRO192x
 * Hungdvt - FX11656
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LuckyNumber {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        // Game record
        double gameCounts = 0;
        double guessCounts = 0;
        ArrayList<Integer> guessRecord = new ArrayList<Integer>();

        // Game play
        boolean play = true;
        while (play) {
            gameCounts++;
            int guessValue = randomNum();
            int attempts = 0;
            boolean makeGuess = false;
            System.out.println("I'm thinking of a number between 0 and 100...");
            while (!makeGuess) {
                guessCounts++;
                attempts++;
                makeGuess = makeGuess(console, guessValue);
            }
            System.out.println("You got it right in " + attempts + " guesses!");
            guessRecord.add(attempts);
            play = playAgain(console);
            System.out.println();
        }

        // Game result
        System.out.println("Overall results: ");
        System.out.println("Total games: " + gameCounts);
        System.out.println("Total guesses: " + guessCounts);
        System.out.println("Guesses / games: " + (double) Math.round(guessCounts / gameCounts * 10) / 10);
        System.out.println("Best game: " + Collections.min(guessRecord));
        System.out.println("Worst game: " + Collections.max(guessRecord));
    }

    /**
     * Generate random integer number
     * @return integer number in range 1 to 100
     */
    public static int randomNum() {
        return (int) (Math.random() * 100);
    }

    /**
     * Prompt user input and compare input value
     * @param console user input
     * @param guessValue generated random number of this game play
     * @return guess true or false
     */
    public static boolean makeGuess(Scanner console, int guessValue) {
        try {
            System.out.print("You guess?? ");
            int guessNum = console.nextInt();
            if (guessNum < guessValue) {
                System.out.println("It's lower.");
                return false;
            }
            if (guessNum > guessValue) {
                System.out.println("It's higher.");
                return false;
            }
            return true;
        } catch (InputMismatchException e) {
            console.next();
            System.out.println("Wrong input! Try again.");
            return makeGuess(console, guessValue);
        }
    }

    /**
     * Prompt user input for next game play
     * @param console user input
     * @return answer true or false
     */
    public static boolean playAgain(Scanner console) {
        System.out.print("Do you want to play again? ");
        String answer = console.next();
        switch (answer.toLowerCase()) {
            case "y":
            case "yes":
                return true;
            default:
                return false;
        }
    }
}
