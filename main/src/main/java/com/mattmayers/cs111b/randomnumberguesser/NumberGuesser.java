package com.mattmayers.cs111b.randomnumberguesser;

public class NumberGuesser {
    private final int initialLowerBound;
    private final int initialUpperBound;

    protected int lowerBound;
    protected int upperBound;
    protected Integer midpoint;

    public NumberGuesser(int initialLowerBound, int initialUpperBound) {
        this.initialLowerBound = initialLowerBound;
        this.initialUpperBound = initialUpperBound;
        reset();
    }

    public void reset() {
        this.lowerBound = initialLowerBound;
        this.upperBound = initialUpperBound;
        midpoint = null;
    }

    public void higher() {
        lowerBound = midpoint + 1;
        if (lowerBound <= upperBound) {
            midpoint = null;
        }
    }

    public void lower() {
        upperBound = midpoint - 1;
        if (lowerBound <= upperBound) {
            midpoint = null;
        }
    }

    public int getCurrentGuess() {
        if (midpoint == null) {
            calculateMidpoint();
        }
        return midpoint;
    }

    protected void calculateMidpoint() {
        midpoint = (lowerBound + upperBound) / 2;
    }
}
