package com.andycode;

import javax.swing.*;
import java.awt.*;

public class UtilityTimerEx extends JFrame{
    public UtilityTimerEx() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setResizable(false);
        pack();

        setTitle("Star");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }
}
