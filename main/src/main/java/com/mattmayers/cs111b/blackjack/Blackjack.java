package com.mattmayers.cs111b.blackjack;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        new Blackjack().run();
    }

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private int total = 0;
    private boolean playAgain = true;

    private void run() {
        int nextCard;
        boolean anotherCard;
        while (playAgain) {
            total = 0;
            anotherCard = true;
            // draw first two cards, display them to the user, and add them to the total
            System.out.print("First cards: ");
            total = drawCard();
            nextCard = drawCard();
            System.out.printf("%d, %d\n", total, nextCard);
            total += nextCard;
            printTotal();

            while (anotherCard) {
                System.out.print("Do you want another card? (y/n): ");
                anotherCard = shouldContinue();

                if (anotherCard) {
                    nextCard = drawCard();
                    System.out.printf("Card: %d\n", nextCard);
                    total += nextCard;
                    printTotal();
                    if (isBust()) {
                        System.out.println("Bust.");
                        anotherCard = false;
                    }
                }
            }
            System.out.print("Would you like to play again? (y/n): ");
            playAgain = shouldContinue();
            System.out.println();
        }
    }

    private boolean isBust() {
        return total > 21;
    }

    private void printTotal() {
        System.out.printf("Total: %d\n", total);
    }

    private boolean shouldContinue() {
        final char input =  scanner.next().charAt(0);
        return input == 'y' || input == 'Y';
    }

    private int drawCard() {
        return random.nextInt(10)+1;
    }
}