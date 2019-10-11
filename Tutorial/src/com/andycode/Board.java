package com.andycode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceShip;
    private List<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };


    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics graphics) {
        if (spaceShip.isVisible()) {
            graphics.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
        }

        List<Missile> ms = spaceShip.getMissileList();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                graphics.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            }
        }

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                graphics.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }

        graphics.setColor(Color.white);
        graphics.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics graphics) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = getFontMetrics(small);

        graphics.setColor(Color.white);
        graphics.setFont(small);
        graphics.drawString(msg, (B_WIDTH - fontMetrics.stringWidth(msg)) / 2,
                B_HEIGHT / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        updateShip();
        updateMissiles();
        updateAliens();
        checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateShip() {
        if (spaceShip.isVisible()) {
            spaceShip.move();
        }
    }

    private void updateMissiles() {
        List<Missile> missiles = spaceShip.getMissileList();
        for (int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            if (missile.isVisible()) {
                missile.move();
            } else {
                missiles.remove(i);
            }
        }
    }

    private void updateAliens() {
        if (aliens.isEmpty()) {
            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {
            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {
        Rectangle rectangle = spaceShip.getBounds();

        for (Alien alien : aliens) {
            Rectangle alienRectangle = alien.getBounds();
            if (alienRectangle.intersects(rectangle)) {
                spaceShip.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        List<Missile> ms = spaceShip.getMissileList();

        for (Missile m : ms) {
            Rectangle missibleRectangle = m.getBounds();

            for (Alien alien : aliens) {
                Rectangle alienRectangle = alien.getBounds();
                if (missibleRectangle.intersects(alienRectangle)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }

    }

}
