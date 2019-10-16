package com.mattmayers.cs111b.circle;

// Matthew Mayers

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyCircleTester {
    private static final int MIN_X = 100;
    private static final int MIN_Y = 100;
    private static final int MIN_RADIUS = 10;
    private static final int MAX_RADIUS = 200;

    public static void main(String[] args) {
        new MyCircleTester().run();
    }

    private final List<MyCircle> circles = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    private JFrame window;
    private CircleCanvas canvas;

    private void run() {
        initRenderWindow();

        // prescribed circles
        createPrescribedCircles();
        printCircleInfo();
        render();

        System.out.println("Next we'll do some randomly-generated circles.");
        System.out.print("Hit enter to continue...");
        waitForInput();
        System.out.println();

        reset();

        // randomly generated circles
        createRandomCircles();
        printCircleInfo();
        render();

        System.out.print("Hit enter to exit...");
        waitForInput();

        // tell the window to close so we can terminate
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    private void createPrescribedCircles() {
        circles.add(new MyCircle(100, 100, 50));
        circles.add(new MyCircle(200, 200, 100));
        circles.add(new MyCircle(500, 500, 200));
    }

    /**
     * Create 5 randomly-sized, randomly-placed circles
     */
    private void createRandomCircles() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt((window.getWidth()-MIN_X*2)+1) + MIN_X;
            int y = rand.nextInt((window.getHeight()-MIN_Y*2)+1) + MIN_Y;
            int radius = rand.nextInt((MAX_RADIUS-MIN_RADIUS)+1) + MIN_RADIUS;
            circles.add(new MyCircle(x, y, radius));
        }
    }

    private void printCircleInfo() {
        for (int i = 0; i < circles.size(); i++) {
            MyCircle current = circles.get(i);
            List<String> overlaps = new ArrayList<>();
            for (int j = 0; j < circles.size(); j++) {
                if (j == i) {
                    continue;
                }
                MyCircle other = circles.get(j);
                if (current.doesOverlap(other)) {
                    overlaps.add(String.format("Circle %d", j));
                }
            }

            System.out.printf("Circle %d area: %f\n", i, current.getArea());
            System.out.printf("Circle %d overlaps with: %s\n\n", i, String.join(", ", overlaps));
        }
    }

    private void initRenderWindow() {
        window = new JFrame("Circle Tester");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new CircleCanvas();
        window.getContentPane().add(canvas);
        window.setSize(800, 800);
        window.setVisible(true);
    }

    private void render() {
        canvas.setCircles(circles);
        canvas.repaint();
    }

    private void reset() {
        circles.clear();
    }

    private void waitForInput() {
        scanner.nextLine();
    }
}
