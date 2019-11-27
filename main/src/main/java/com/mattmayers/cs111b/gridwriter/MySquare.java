package com.mattmayers.cs111b.gridwriter;

public class MySquare extends GridItem {
    private final int side;

    public MySquare(int x, int y, int side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public double getArea() {
        return side * side;
    }

    public boolean containsPoint(int xValue, int yValue) {
        return	xValue >= x &&
                xValue <= x + side &&
                yValue >= y &&
                yValue <= y + side;
    }
}
