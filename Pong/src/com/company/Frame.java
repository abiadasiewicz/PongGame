package com.company;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel;
    Frame()
    {
        panel=new Panel();
        this.add(panel);
        this.setTitle("Pong");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);

    }
}
