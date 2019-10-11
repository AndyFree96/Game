package com.andycode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceShip;
    private final int DELAY = 10;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        spaceShip = new SpaceShip();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    private void step() {
        spaceShip.move();

        repaint(spaceShip.getX() - 1, spaceShip.getY() - 1,
                spaceShip.getWidth() + 2, spaceShip.getHeight() + 2);
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
