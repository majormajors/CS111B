package com.mattmayers.cs111b.randomnumberguesser;

import java.util.Scanner;

public class GuessingProgram {
    private static final int DEFAULT_LOWER_BOUND = 1;
    private static final int DEFAULT_UPPER_BOUND = 100;

    public static void main(String[] args) {
        boolean random = true;
        int lowerBound = DEFAULT_LOWER_BOUND;
        int upperBound = DEFAULT_UPPER_BOUND;
        String arg;
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                arg = args[i];
                switch (arg) {
                    case "-h":
                        printHelp();
                        System.exit(0);
                        break;
                    case "-nr":
                        random = false;
                        break;
                    case "-l":
                        lowerBound = readArgValue(args, i);
                        i++;
                        break;
                    case "-u":
                        upperBound = readArgValue(args, i);
                        i++;
                        break;
                }
            }
        }
        verifyBounds(lowerBound, upperBound);
        new GuessingProgram(random, lowerBound, upperBound).run();
    }

    private static void printHelp() {
        System.out.println("usage: java GuessingProgram [-h] [-l <lower>] [-u <upper>] [-nr]\n");
        System.out.println("\t        -h\tPrint this help");
        System.out.println("\t       -nr\tNon-random; Don't randomize guesses");
        System.out.println("\t-l <lower>\tSet lower bound");
        System.out.println("\t-u <upper>\tSet upper bound");
    }

    private static int readArgValue(String[] args, int i) {
        int value = 0;
        if (i+1 >= args.length) {
            System.err.printf("Missing value for argument %s\n", args[i]);
            System.exit(1);
        }
        try {
            value = Integer.parseInt(args[i+1]);
        } catch (NumberFormatException e) {
            System.err.printf("Argument value is not a number: %s\n", args[i+1]);
            System.exit(1);
        }
        return value;
    }

    private static void verifyBounds(int l, int u) {
        if (l >= u) {
            System.err.println("Lower bound cannot be greater than or equal to upper bound");
            System.err.printf("lower: %d, upper: %d\n", l, u);
            System.exit(1);
        }
    }

    private final NumberGuesser guesser;
    private final Scanner in = new Scanner(System.in);

    private GuessingProgram(boolean random, int lowerBound, int upperBound) {
        if (random) {
            guesser = new RandomNumberGuesser(lowerBound, upperBound);
        } else {
            guesser = new NumberGuesser(lowerBound, upperBound);
        }
    }

    private void run() {
        do {
            playOneGame();
        } while (shouldPlayAgain());
    }

    private void playOneGame() {
        guesser.reset();
        int guess;
        char response;
        do {
            guess = guesser.getCurrentGuess();
            response = getUserResponseToGuess(guess);
            try {
                if (response == 'h') {
                    guesser.higher();
                } else if (response == 'l') {
                    guesser.lower();
                }
            } catch (IllegalStateException e) {
                System.out.println("ERROR: " + e.getMessage());
                break;
            }
        } while (response != 'c');
    }

    private boolean shouldPlayAgain() {
        System.out.print("Great! Do you want to play again? (y/n): ");
        char input = in.next().charAt(0);
        return input == 'y' || input == 'Y';
    }

    private char getUserResponseToGuess(int guess) {
        char input;
        do {
            System.out.printf("Is it %d?\t(h/l/c): ", guess);
            input = in.next().charAt(0);
        } while (input != 'h' && input != 'l' && input != 'c');
        return input;
    }
}