package com.mattmayers.cs111b.circle2;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.util.*;

public class CircleProgram {
    private static final int MIN_X = 100;
    private static final int MIN_Y = 100;
    private static final int MIN_RADIUS = 10;
    private static final int MAX_RADIUS = 200;

    public static void main(String[] args) {
        new CircleProgram().run();
    }

    private final List<Circle> circles = new ArrayList<>();

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
        // old x, y, and radius constructor
        final Circle c0 = new Circle(100, 100, 50);
        // no-arg constructor with setCenter
        final Circle c1 = new Circle();
        c1.setCenter(c0.getCenter());
        // no-arg constructor with setX, setY, and setRadius
        final Circle c2 = new Circle();
        c2.setX(200);
        c2.setY(200);
        c2.setRadius(100);
        // Point and radius constructor
        final Circle c3 = new Circle(new Point(250, 250), 50);
        // copy constructor
        final Circle c4 = new Circle(c3);
        // another Point and radius constructor
        final Circle c5 = new Circle(new Point(400, 400), 75);
        circles.addAll(Arrays.asList(c0, c1, c2, c3, c4, c5));
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
            circles.add(new Circle(x, y, radius));
        }
    }

    private void printCircleInfo() {
        for (int i = 0; i < circles.size(); i++) {
            Circle current = circles.get(i);
            List<String> equals = new ArrayList<>();
            List<String> overlaps = new ArrayList<>();
            for (int j = 0; j < circles.size(); j++) {
                if (j == i) {
                    continue;
                }
                Circle other = circles.get(j);
                String name = String.format("Circle %d", j);
                if (current.equals(other)) {
                    equals.add(name);
                }
                if (current.doesOverlap(other)) {
                    overlaps.add(name);
                }
            }
            System.out.printf("# Circle %d\n", i);
            System.out.printf("Center: (%.2f,%.2f)\n", current.getX(), current.getY());
            System.out.printf("Radius: %.2f\n", current.getRadius());
            System.out.printf("Area: %.2f\n", current.getArea());
            System.out.printf("Equal to: %s\n", String.join(", ", equals));
            System.out.printf("Overlaps with: %s\n", String.join(", ", overlaps));
            System.out.println(current.toString());
            System.out.println();
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
