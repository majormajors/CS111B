package com.mattmayers.cs111b.stringprocessor;

import java.util.Scanner;

public class StringProcessorProgram {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        StringProcessor sp = new StringProcessor();

        do {
            System.out.print("Enter a line of text: ");
            sp.setString(in.nextLine());
            System.out.printf("Words: %d\n", sp.wordCount());
            System.out.printf("Uppercase: %d\n", sp.uppercaseCount());
            System.out.printf("Digits: %d\n", sp.digitCount());
            System.out.printf("Digit words: %d\n", sp.digitWordCount());
            System.out.printf("Line with whitespace removed: %s\n", sp.getNoSpaceString());
            System.out.printf("Line with vowels replaced: %s\n", sp.getNoVowelString());
            System.out.printf("Line with digit words replaced: %s\n\n", sp.getNoDigitWordString());

            // ask to go again
            System.out.print("Do you want to enter another? (y/n): ");

        } while (Character.toLowerCase(in.nextLine().charAt(0)) == 'y');
    }
}
