package com.mattmayers.cs111b.stringprocessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE = "\\s+";
    private static final Set<Character> VOWEL_SET = new HashSet<>(Arrays.asList('a','e','i','o','u'));
    private static final Pattern DIGIT_WORD_PATTERN = Pattern.compile("(?i)\\b(zero|one|two|three|four|five|six|seven|eight|nine)\\b");
    private static final Map<String, String> DIGIT_WORD_INTEGER_MAP = new HashMap<String, String>() {{
        put("zero", "0");
        put("one", "1");
        put("two", "2");
        put("three", "3");
        put("four", "4");
        put("five", "5");
        put("six", "6");
        put("seven", "7");
        put("eight", "8");
        put("nine", "9");
    }};

    private String string;

    private int wordCount = 0;
    private int uppercaseCount = 0;
    private int digitCount = 0;
    private int digitWordCount = 0;
    private String noSpaceString = EMPTY_STRING;
    private String noVowelString = EMPTY_STRING;
    private String noDigitWordString = EMPTY_STRING;

    public StringProcessor(final String string) {
        this.string = string;
        computeValues();
    }

    public StringProcessor() {
        this(EMPTY_STRING);
    }

    public String getString() {
        return string;
    }

    public void setString(final String string) {
        this.string = string;
        computeValues();
    }

    public int wordCount() {
        return wordCount;
    }

    public int uppercaseCount() {
        return uppercaseCount;
    }

    public int digitCount() {
        return digitCount;
    }

    public int digitWordCount() {
        return digitWordCount;
    }

    public String getNoSpaceString() {
        return noSpaceString;
    }

    public String getNoVowelString() {
        return noVowelString;
    }

    public String getNoDigitWordString() {
        return noDigitWordString;
    }

    private void computeValues() {
        if (string.length() == 0) {
            return;
        }

        final StringBuilder noSpaceStringBuilder = new StringBuilder();
        final StringBuilder noVowelStringBuilder = new StringBuilder();
        int upperCaseCount = 0;
        int digitCount = 0;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (!Character.isWhitespace(c)) {
                noSpaceStringBuilder.append(c);
            }
            if (VOWEL_SET.contains(Character.toLowerCase(c))) {
                noVowelStringBuilder.append('-');
            } else {
                noVowelStringBuilder.append(c);
            }
            if (Character.isUpperCase(c)) {
                ++upperCaseCount;
            } else if (Character.isDigit(c)) {
                ++digitCount;
            }
        }
        this.noSpaceString = noSpaceStringBuilder.toString();
        this.noVowelString = noVowelStringBuilder.toString();
        this.uppercaseCount = upperCaseCount;
        this.digitCount = digitCount;

        final StringBuilder noDigitWordStringBuilder = new StringBuilder();
        int wordCount = 0;
        int digitWordCount = 0;
        String[] words = string.trim().split(WHITESPACE);
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            if (!word.trim().isEmpty()) {
                ++wordCount;
                Matcher matcher = DIGIT_WORD_PATTERN.matcher(word);
                if (matcher.matches()) {
                    ++digitWordCount;
                    String dw = matcher.group(1);
                    String intString = DIGIT_WORD_INTEGER_MAP.get(dw.toLowerCase());
                    noDigitWordStringBuilder.append(matcher.replaceFirst(intString));
                } else {
                    noDigitWordStringBuilder.append(word);
                }
                if (i < words.length-1) {
                    noDigitWordStringBuilder.append(" ");
                }
            }
        }
        this.noDigitWordString = noDigitWordStringBuilder.toString();
        this.wordCount = wordCount;
        this.digitWordCount = digitWordCount;
    }
}