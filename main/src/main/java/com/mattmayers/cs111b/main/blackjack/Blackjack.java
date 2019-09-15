package com.mattmayers.cs111b.main.blackjack;

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
    private boolean anotherCard = true;

    private void run() {
        char input;
        int nextCard;
        while (playAgain) {
            reset();
            // draw first two cards, display them to the user, and add them to the total
            System.out.print("First cards: ");
            total = drawCard();
            nextCard = drawCard();
            System.out.printf("%d, %d\n", total, nextCard);
            total += nextCard;
            printTotal();

            while (anotherCard) {
                System.out.print("Do you want another card? (y/n): ");
                input = readInput();
                anotherCard = input == 'y' || input == 'Y';

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
            input = readInput();
            playAgain = input == 'y' || input == 'Y';
        }
    }

    private boolean isBust() {
        return total > 21;
    }

    private void printTotal() {
        System.out.printf("Total: %d\n", total);
    }

    private char readInput() {
        return scanner.next().charAt(0);
    }

    private int drawCard() {
        return random.nextInt(9)+1;
    }

    private void reset() {
        total = 0;
        anotherCard = true;
    }
}