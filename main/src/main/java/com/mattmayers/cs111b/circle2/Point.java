package com.mattmayers.cs111b.circle2;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - x, 2.0) + Math.pow(other.y - y, 2.0));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            throw new IllegalArgumentException("not a Point object");
        }
        Point other = (Point)obj;
        return x == other.x && y == other.y;
    }
}
