package com.mattmayers.cs111b.circle2;

public class Circle {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = new Point(center);
        this.radius = radius;
    }

    public Circle(double x, double y, double radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public Circle() {
        this.center = new Point();
        this.radius = 1;
    }

    public Circle(Circle c) {
        this.center = new Point(c.center);
        this.radius = c.radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = new Point(center);
    }

    public double getX() {
        return center.getX();
    }

    public void setX(double x) {
        center.setX(x);
    }

    public double getY() {
        return center.getY();
    }

    public void setY(double y) {
        center.setY(y);
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

    public boolean doesOverlap(Circle other) {
        return center.distance(other.center) <= radius + other.radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Circle)) {
            throw new IllegalArgumentException("not a Point object");
        }
        Circle other = (Circle)obj;
        return center.equals(other.center) && radius == other.radius;
    }

    @Override
    public String toString() {
        return String.format("[Circle(x=%f, y=%f, radius=%f)]", center.getX(), center.getY(), radius);
    }
}