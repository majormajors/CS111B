package com.mattmayers.cs111b.highscores;

import java.util.ArrayList;
import java.util.Scanner;

public class HighScores {
    public static void main(String[] args) {
        final ArrayList<String> names = new ArrayList<>();
        final ArrayList<Integer> scores = new ArrayList<>();
        initialize(names, scores);
        sort(names, scores);
        display(names, scores);
    }

    private static void initialize(ArrayList<String> names, ArrayList<Integer> scores) {
        final Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.printf("Enter the name for score #%d: ", i+1);
            names.add(in.nextLine());
            System.out.printf("Enter the score for score #%d: ", i+1);
            scores.add(Integer.parseInt(in.nextLine()));
        }
        System.out.println();
        in.close();
    }

    /**
     * Simple bubble sort
     * @param names
     * @param scores
     */
    private static void sort(ArrayList<String> names, ArrayList<Integer> scores) {
        int swaps;
        do {
            swaps = 0;
            for (int i = 0; i < scores.size()-1; i++) {
                int currentScore = scores.get(i);
                int nextScore = scores.get(i+1);
                if (currentScore < nextScore) {
                    scores.set(i, nextScore);
                    scores.set(i+1, currentScore);

                    String tempName = names.get(i);
                    names.set(i, names.get(i+1));
                    names.set(i+1, tempName);

                    swaps++;
                }
            }
        } while (swaps > 0);
    }

    private static void display(ArrayList<String> names, ArrayList<Integer> scores) {
        System.out.println("Top Scorers:");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%s: %d\n", names.get(i), scores.get(i));
        }
    }
}