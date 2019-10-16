package com.mattmayers.cs111b.circle;

// Matthew Mayers

public class MyCircle {
    private double x;
    private double y;
    private double radius;

    public MyCircle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2.0);
    }

    public boolean doesOverlap(MyCircle other) {
        return distanceTo(other) <= radius + other.radius;
    }

    public double distanceTo(MyCircle other) {
        return Math.sqrt(Math.pow(other.x - x, 2.0) + Math.pow(other.y - y, 2.0));
    }
}
