package com.mattmayers.cs111b.main.memorization;

// Matthew Mayers

import java.util.Scanner;

public class Asterisks {
    public static void main(String[] args) {
        int count;
        int goAgain = 'y';
        final Scanner input = new Scanner(System.in);

        while (goAgain == 'y') {
            System.out.print("How many asterisks?: ");
            count = input.nextInt();

            for (int i = 0; i < count; i++) {
                System.out.print("*");
            }

            System.out.println();
            System.out.print("Go again? (y/n): ");

            goAgain = input.next().charAt(0);
        }
    }
}