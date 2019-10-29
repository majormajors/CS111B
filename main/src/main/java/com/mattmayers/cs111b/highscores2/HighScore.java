package com.mattmayers.cs111b.highscores2;

public class HighScore implements Comparable {
    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object other) {
        if (!(other instanceof HighScore)) {
            throw new IllegalArgumentException();
        }
        return Integer.compare(score, ((HighScore) other).score);
    }
}
