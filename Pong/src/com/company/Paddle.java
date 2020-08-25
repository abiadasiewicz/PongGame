package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends Rectangle{
 int id;
 int speed = 10;
 int ySpeed;

 Paddle(int x, int y, int width, int height, int id)
 {
    super(x, y, width, height);
    this.id=id;
 }

 public void draw(Graphics g)
 {
     if(id==1)
     {
         g.setColor(new Color(10, 202, 217));
         g.fillRect(x,y,width,height);
     }
     else {
         g.setColor(new Color(194, 14, 36));
         g.fillRect(x, y, width, height);
     }
 }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setDirection(speed);
                    move();
                }
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setDirection(0);
                    move();
                }
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setDirection(0);
                    move();
                }
                break;
        }
    }

    public void move()
    {
        y+=ySpeed;
    }

    public void setDirection(int yDirection)
    {
        ySpeed=yDirection;
    }
}
