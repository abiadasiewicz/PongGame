package com.company;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
Random random;
double xSpeed;
double ySpeed;

    Ball(int x, int y, int widht, int height)
    {
        super(x,y,widht,height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
        {
            randomXDirection--;
        }
        setXDirection(2*randomXDirection);
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
        {
            randomYDirection--;
        }
        setYDirection(2*randomYDirection);
    }

    public void setXDirection(double randomXDirection)
    {
        xSpeed= randomXDirection;
    }

    public void setYDirection(double randomYDirection)
    {
        ySpeed = randomYDirection;
    }

    public void move()
    {
        x+=xSpeed;
        y+=ySpeed;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,width,height);
    }
}
