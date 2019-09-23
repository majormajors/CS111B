package com.mattmayers.cs111b.numberguesser;

// Matthew Mayers

import java.util.Scanner;

public class NumberGuesser {
    private static final int LOW = 1;
    private static final int HIGH = 100;
    private static final Scanner in = new Scanner(System.in);

    public static void main(String [] args) {
        do {
            playOneGame();
        } while (shouldPlayAgain());
    }

    private static void playOneGame() {
        System.out.printf("Think of a number between %d and %d.\n", LOW, HIGH);

        int low = LOW;
        int high = HIGH;
        int mid;
        char response;

        while (low <= high) {
            mid = getMidpoint(low, high);
            response = getUserResponseToGuess(mid);
            if (response == 'h') {
                low = mid + 1;
            } else if (response == 'l') {
                high = mid - 1;
            } else if (response == 'c') {
                break;
            } else {
                throw new IllegalStateException("Invalid response " + response);
            }
        }
    }

    private static boolean shouldPlayAgain() {
        System.out.print("Great! Do you want to play again? (y/n): ");
        char input = in.next().charAt(0);
        return input == 'y' || input == 'Y';
    }

    private static char getUserResponseToGuess(int guess) {
        char input;
        do {
            System.out.printf("Is it %d?\t(h/l/c): ", guess);
            input = in.next().charAt(0);
        } while (input != 'h' && input != 'l' && input != 'c');
        return input;
    }

    private static int getMidpoint(int low, int high) {
        return (low + high) / 2;
    }
}