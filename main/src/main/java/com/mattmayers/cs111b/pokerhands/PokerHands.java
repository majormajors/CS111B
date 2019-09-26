package com.mattmayers.cs111b.pokerhands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PokerHands {
    private static final Scanner in = new Scanner(System.in);
    private static final Map<Integer, Integer> counts = new HashMap<>();

    public static void main(String[] args) {
        int[] hand = new int[5];
        do {
            System.out.println("Enter five numeric cards, no face cards. Use 2-9.");
            for (int i = 0; i < hand.length; i++) {
                hand[i] = askUserForCard(i + 1);
            }
            System.out.println(findBestHand(hand));
        } while (askKeepPlaying());
    }

    private static int askUserForCard(int number) {
        int input;
        do {
            System.out.printf("Card %d: ", number);
            input = in.nextInt();
        } while (input < 2 || input > 9);
        return input;
    }

    private static boolean askKeepPlaying() {
        System.out.print("Go again? (y/n): ");
        return in.next().charAt(0) == 'y';
    }

    private static String findBestHand(int[] hand) {
        findCounts(hand);
        if (containsFourOfAKind(hand)) {
            return "Four of a Kind!";
        } else if (containsFullHouse(hand)) {
            return "Full House!";
        } else if (containsStraight(hand)) {
            return "Straight!";
        } else if (containsThreeOfAKind(hand)) {
            return "Three of a Kind!";
        } else if (containsTwoPair(hand)) {
            return "Two Pair!";
        } else if (containsPair(hand)) {
            return "Pair!";
        } else {
            return "High Card!";
        }
    }

    private static boolean containsPair(int[] hand) {
        return counts.size() == 4;
    }

    private static boolean containsTwoPair(int[] hand) {
        return counts.size() == 3 && counts.containsValue(2);
    }

    private static boolean containsStraight(int[] hand) {
        if (counts.size() < 5) {
            return false;
        }
        Arrays.sort(hand); // We only need the array to be sorted at this point, so don't do it until here
        return hand[1] == hand[0]+1 &&
                hand[2] == hand[1]+1 &&
                hand[3] == hand[2]+1 &&
                hand[4] == hand[3]+1;
    }

    private static boolean containsFullHouse(int[] hand) {
        return counts.size() == 2 && counts.containsValue(2);
    }

    private static boolean containsThreeOfAKind(int[] hand) {
        return containsNOfAKind(hand, 3);
    }

    private static boolean containsFourOfAKind(int[] hand) {
        return containsNOfAKind(hand, 4);
    }

    private static boolean containsNOfAKind(int[] hand, int n) {
        return n + counts.size() == 6 && counts.containsValue(n);
    }

    /**
     * Build a {@link HashMap} of card values to counts of the number of times they appear in the array
     * @param hand
     */
    private static void findCounts(int[] hand) {
        counts.clear();
        for (int c : hand) {
            counts.compute(c, (k, v) -> v == null ? 1 : v+1);
        }
    }
}