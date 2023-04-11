package org.nhn.ballworld;

import java.awt.Graphics;
import java.awt.Frame;
import java.util.List;
import java.util.LinkedList;
import java.awt.Rectangle;

/**
 * 여러개의 볼
 */
public class MultiBallWorld extends Frame {

    List<Ball> balls;
    Rectangle region;

    /**
     * 볼의 공간
     * @param width 넓이
     * @param height 높이
     */
    public MultiBallWorld(int width, int height) {

        super();
        setTitle("Multi Ball World");
        setSize(width, height);

        balls = new LinkedList<>();


    }

    /**
     * 공 여러개
     * @param ball 공
     */
    public void add (Ball ball) {
        balls.add(ball);

    }

    @Override
    public void paint (Graphics graphics) {
        super.paint(graphics);
        for ( Ball ball: balls) {
            ball.paint(graphics);
        }
    }

    public void run() {
        try {
            for ( Ball ball: balls) {
                ball.setDisplacement(10);
                ball.setDisplacement(10);
            }
            while(true) {
                for (Ball ball: balls) {
                    ball.move();
                    System.out.println("Collision: ");
                }
                
                repaint();

                Thread.sleep(100);
                System.out.println(System.currentTimeMillis());
            }
        }
        catch (InterruptedException e) {

        }
    }
}