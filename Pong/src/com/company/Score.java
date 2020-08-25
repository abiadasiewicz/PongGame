package com.company;

import java.awt.*;

public class Score extends Rectangle {
    static int WIDTH;
    static int HEIGHT;
    int player1 = 0;
    int player2 = 0;
    static int OVAL_WIDTH = 100;
    static int OVAL_HEIGHT = 100;

    Score(int width, int height) {
        Score.WIDTH = width;
        Score.HEIGHT = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 50));

        g.drawOval(WIDTH / 2 - OVAL_WIDTH / 2, HEIGHT / 2 - OVAL_HEIGHT / 2, OVAL_WIDTH, OVAL_HEIGHT);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (WIDTH / 2) - 100, 50);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (WIDTH / 2) + 40, 50);
    }
}
