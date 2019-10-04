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

    private static void sort(ArrayList<String> names, ArrayList<Integer> scores) {
        selectionSort(names, scores);
    }

    private static void bubbleSort(ArrayList<String> names, ArrayList<Integer> scores) {
        boolean swap;
        do {
            swap = false;
            for (int i = 0; i < scores.size()-1; i++) {
                if (scores.get(i) < scores.get(i+1)) {
                    swap(names, scores, i, i+1);
                    swap = true;
                }
            }
        } while (swap);
    }

    private static void selectionSort(ArrayList<String> names, ArrayList<Integer> scores) {
        final int length = scores.size();
        for (int i = 0; i < length-1; i++) {
            int jmax = i;
            for (int j = i + 1; j < length; j++) {
                if (scores.get(j) > scores.get(jmax)) {
                    jmax = j;
                }
            }
            if (jmax != i) {
                swap(names, scores, i, jmax);
            }
        }
    }

    private static void swap(ArrayList<String> names, ArrayList<Integer> scores, int i1, int i2) {
        final String nameTemp = names.get(i1);
        final Integer scoreTemp = scores.get(i1);
        names.set(i1, names.get(i2));
        scores.set(i1, scores.get(i2));
        names.set(i2, nameTemp);
        scores.set(i2, scoreTemp);
    }

    private static void display(ArrayList<String> names, ArrayList<Integer> scores) {
        System.out.println("Top Scorers:");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%s: %d\n", names.get(i), scores.get(i));
        }
    }
}