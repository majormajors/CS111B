package com.mattmayers.cs111b.circle;

// Matthew Mayers

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CircleCanvas extends JPanel {
    private List<MyCircle> circles = new ArrayList<>();

    public void setCircles(List<MyCircle> circles) {
        this.circles = new ArrayList<>(circles);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < circles.size(); i++) {
            MyCircle circle = circles.get(i);
            int left = (int)Math.round(circle.getX() - circle.getRadius());
            int top = (int)Math.round(circle.getY() - circle.getRadius());
            int size = (int)Math.round(circle.getRadius() * 2.0);
            g.drawOval(left, top, size, size);

            if (g instanceof Graphics2D) {
                FontRenderContext frc = ((Graphics2D)g).getFontRenderContext();
                String name = String.format("Circle %d", i);
                Rectangle2D stringRect = g.getFont().getStringBounds(name, frc);
                int labelX = (int)Math.round(circle.getX() - stringRect.getWidth() / 2);
                int labelY = (int)Math.round(circle.getY() + stringRect.getHeight() / 2);
                g.drawString(name, labelX, labelY);
            }
        }
    }
}
