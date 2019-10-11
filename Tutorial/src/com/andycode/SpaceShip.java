package com.andycode;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite{
    private int dx;
    private int dy;
    private List<Missile> missileList;

    public SpaceShip(int x, int y) {
        super(x, y);
        initSpaceShip();
    }

    private void initSpaceShip() {
        missileList = new ArrayList<>();

        loadImage("src/resources/spaceship.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public List<Missile> getMissileList() {
        return missileList;
    }


    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void fire() {
        missileList.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
