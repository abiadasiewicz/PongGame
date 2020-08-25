package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = GAME_WIDTH *5/9;
    static final Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int PADDLE_HEIGHT = 60;
    static final int PADDLE_WIDTH = 20;
    Thread thread;
    Image image;
    Random random;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    Panel()
    {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(dimension);
        this.setFocusable(true);
        thread = new Thread(this);
        thread.start();
        this.addKeyListener(new paddleRun());
    }

    public void newPaddles()
    {
        paddle1 = new Paddle(0,(GAME_HEIGHT /2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2= new Paddle(GAME_WIDTH -PADDLE_WIDTH,(GAME_HEIGHT /2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void newBall()
    {
        ball = new Ball(GAME_WIDTH /2, GAME_HEIGHT /2, 10,10);
    }

    public void paint(Graphics g)
    {
        image=createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g)
    {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public class paddleRun extends KeyAdapter
    {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;

        while (true)
        {
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1)
            {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    private void checkCollision() {

        if(ball.y+ball.getHeight()>= GAME_HEIGHT)
        {
            ball.setYDirection(-ball.ySpeed);
        }
        if(ball.y<=0)
        {
            ball.setYDirection(-ball.ySpeed);
        }
        if(ball.intersects(paddle1))
        {
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.xSpeed++;
            if(ball.ySpeed>0)
            {
                ball.ySpeed+=0.2;
            }
            else
            {
                ball.ySpeed-=0.2;
            }
            ball.setXDirection(ball.xSpeed);
            ball.setYDirection(ball.ySpeed);

        }
        if(ball.intersects(paddle2))
        {
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.xSpeed++;
            if(ball.ySpeed>0)
            {
                ball.ySpeed+=0.2;
            }
            else
            {
                ball.ySpeed-=0.2;
            }
            ball.setXDirection(-ball.xSpeed);
            ball.setYDirection(ball.ySpeed);
        }

        if(paddle1.y+paddle1.getHeight()>= GAME_HEIGHT)
        {
            paddle1.y= (int) (GAME_HEIGHT -paddle1.getHeight());
        }

        if(paddle1.y<=0)
        {
            paddle1.y= 0;
        }

        if(paddle2.y+paddle2.getHeight()>= GAME_HEIGHT)
        {
            paddle2.y= (int) (GAME_HEIGHT -paddle2.getHeight());;
        }

        if(paddle2.y<=0)
        {
            paddle2.y= 0;
        }

        if(ball.x<=0)
        {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }

        if(ball.x>= GAME_WIDTH -ball.getWidth())
        {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }
    }

    private void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

}
