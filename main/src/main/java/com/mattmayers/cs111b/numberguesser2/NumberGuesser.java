package com.mattmayers.cs111b.numberguesser2;

import java.util.Random;

public class NumberGuesser {
    private final Random rng = new Random();
    private final int initialLowerBound;
    private final int initialUpperBound;
    private final boolean random;

    private int lowerBound;
    private int upperBound;
    private int midpoint;

    public NumberGuesser(boolean random, int initialLowerBound, int initialUpperBound) {
        this.random = random;
        this.initialLowerBound = initialLowerBound;
        this.initialUpperBound = initialUpperBound;
        reset();
    }

    public void reset() {
        this.lowerBound = initialLowerBound;
        this.upperBound = initialUpperBound;
        calculateMidpoint();
    }

    public void higher() {
        lowerBound = midpoint + 1;
        if (lowerBound <= upperBound) {
            calculateMidpoint();
        }
    }

    public void lower() {
        upperBound = midpoint - 1;
        if (lowerBound <= upperBound) {
            calculateMidpoint();
        }
    }

    public int getCurrentGuess() {
        return midpoint;
    }

    private void calculateMidpoint() {
        if (random) {
            midpoint = rng.nextInt((upperBound - lowerBound) + 1) + lowerBound;
        } else {
            midpoint = (lowerBound + upperBound) / 2;
        }
    }
}
