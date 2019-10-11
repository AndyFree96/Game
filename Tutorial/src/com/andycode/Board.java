package com.andycode;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Board extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDonut(g);
    }

    private void drawDonut(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(renderingHints);
        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D ellipse2D = new Ellipse2D.Double(0, 0, 80, 130);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform affineTransform =
                    AffineTransform.getTranslateInstance(w / 2, h / 2);
            affineTransform.rotate(Math.toRadians(deg));
            graphics2D.draw(affineTransform.createTransformedShape(ellipse2D));
        }
    }
}
