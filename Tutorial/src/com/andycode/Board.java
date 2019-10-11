package com.andycode;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
        implements Runnable {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image star;
    private Thread animator;
    private int x, y;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

    }

    private void loadImage() {
        ImageIcon imageIcon = new ImageIcon("src/resources/star.png");
        star = imageIcon.getImage();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStar(g);
    }

    private void drawStar(Graphics graphics) {
        graphics.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle() {
        x += 1;
        y += 1;

        if (y > B_HEIGHT) {
            x = INITIAL_X;
            y = INITIAL_Y;
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                String msg = String.format("Thread interrupted: %s", ex.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
