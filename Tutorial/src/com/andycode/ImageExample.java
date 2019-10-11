package com.andycode;

import javax.swing.*;
import java.awt.*;

public class ImageExample extends JFrame {
    public ImageExample() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        pack();

        setTitle("Imgae");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ImageExample ex = new ImageExample();
            ex.setVisible(true);
        });
    }
}
