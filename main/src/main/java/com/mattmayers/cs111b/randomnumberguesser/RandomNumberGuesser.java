package com.mattmayers.cs111b.randomnumberguesser;

import java.util.Random;

public class RandomNumberGuesser extends NumberGuesser {
    private final Random rng = new Random();

    public RandomNumberGuesser(int initialLowerBound, int initialUpperBound) {
        super(initialLowerBound, initialUpperBound);
    }

    @Override
    protected void calculateMidpoint() {
        midpoint = rng.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }
}
