package com.mattmayers.cs111b.highscores2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class HighScoresProgram {
    public static void main(String[] args) {
        HighScore[] scores = new HighScore[5];
        initialize(scores);
        sort(scores);
        display(scores);
    }

    private static void initialize(HighScore[] scores) {
        final Scanner in = new Scanner(System.in);
        String name;
        int score;
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("Enter the name for score #%d: ", i+1);
            name = in.nextLine();
            System.out.printf("Enter the score for score #%d: ", i+1);
            score = Integer.parseInt(in.nextLine());
            scores[i] = new HighScore(name, score);
        }
        System.out.println();
        in.close();
    }

    private static void sort(HighScore[] scores) {
        Arrays.sort(scores, Collections.reverseOrder());
    }

    private static void display(HighScore[] scores) {
        System.out.println("Top Scorers:");
        for (HighScore score : scores) {
            System.out.printf("%s: %d\n", score.getName(), score.getScore());
        }
    }
}
