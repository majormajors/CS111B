package com.mattmayers.cs111b.numberguesser2;

import java.util.Scanner;

public class GuessingProgram {
    private static final int DEFAULT_LOWER_BOUND = 1;
    private static final int DEFAULT_UPPER_BOUND = 100;

    public static void main(String[] args) {
        boolean random = false;
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
                    case "--rand":
                        random = true;
                        break;
                    case "-l":
                        verifyArgValue(args, i);
                        lowerBound = Integer.parseInt(args[++i]);
                        break;
                    case "-u":
                        verifyArgValue(args, i);
                        upperBound = Integer.parseInt(args[++i]);
                        break;
                }
            }
        }
        verifyBounds(lowerBound, upperBound);
        new GuessingProgram(random, lowerBound, upperBound).run();
    }

    private static void printHelp() {
        System.out.println("usage: java GuessingProgram [-h] [-l <lower>] [-u <upper>] [--rand]\n");
        System.out.println("\t        -h\tPrint this help");
        System.out.println("\t    --rand\tRandomize guesses");
        System.out.println("\t-l <lower>\tSet lower bound");
        System.out.println("\t-u <upper>\tSet upper bound");
    }

    private static void verifyArgValue(String[] args, int fPos) {
        int vPos = fPos+1;
        if (vPos >= args.length) {
            System.err.printf("Missing value for argument %s\n", args[fPos]);
            System.exit(1);
        }
        if (!Character.isDigit(args[vPos].charAt(0))) {
            System.err.printf("Argument value is not a number: %s", args[fPos+1]);
            System.exit(1);
        }
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
        guesser = new NumberGuesser(random, lowerBound, upperBound);
    }

    private void run() {
        do {
            playOneGame();
        } while (shouldPlayAgain());
    }

    private void playOneGame() {
        int guess;
        char response;
        do {
            guess = guesser.getCurrentGuess();
            response = getUserResponseToGuess(guess);
            if (response == 'h') {
                guesser.higher();
            } else if (response == 'l') {
                guesser.lower();
            }
        } while (response != 'c');
        guesser.reset();
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