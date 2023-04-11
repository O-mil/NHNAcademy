package org.nhn.ballworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;

/**
 * 공이 존재할 공간
 */
public class SingleBallWorld extends Frame{

    Ball ball;


    /**
     *
     * @param width 공간 넓이
     * @param height 높이
     */
    public SingleBallWorld(int width, int height) {

        super();
        setTitle("Single Ball world");
        setSize(width, height);

        ball = new Ball(new Point(width / 2.0, height / 2.0),
            Math.min(width, height) / 10, Color.pink);
    }

    /**
     * 볼 그리기
     */
    @Override
    public void paint(Graphics graphics) {
        ball.paint(graphics);

    }

}
