package org.nhnacademy;

import java.awt.Frame;
import java.awt.Graphics;

/**
 * FactalTree 클래스.
 */

public class FractalTree extends Frame {

    int startX;
    int startY;
    int angle;
    int length;
    int rotate;
    int growth;
    int depth;

    /**
    * 생성자.
    *
    * @param width width
    * @param height height
    * @param startX startX
    * @param startY startY
    * @param angle angle
    * @param length length
    * @param rotate rotate
    * @param growth growth
    */

    public FractalTree(int width, int height, int startX, int startY,
            int angle, int length, int rotate, int growth) {
        this.startX = startX;
        this.startY = startY;
        this.angle = angle;
        this.length = length;
        this.rotate = rotate;
        this.growth = growth;

        this.setSize(width, height);
    }

    /**
     * 가지 그리기.
     *
     * @param graphics graphics
     * @param startX startX
     * @param startY startY
     * @param degree degree
     * @param length length
     */

    public void branch(Graphics graphics, int startX, int startY, int degree, int length) {
        if (length > 1) {
            int endX = (int) (startX - length * Math.cos(Math.toRadians(degree)));
            int endY = (int) (startY - length * Math.sin(Math.toRadians(degree)));
            int branchLength = (int) (length * growth * 0.01);

            graphics.drawLine(startX, startY, endX, endY);
            branch(graphics, endX, endY, degree - rotate, branchLength);
            branch(graphics, endX, endY, degree + rotate, branchLength);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        branch(graphics, startX, startY, this.angle, this.length);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        FractalTree tree = new FractalTree(500, 500, 250, 450, 90, 100, 30, 75);
        tree.setVisible(true);
    }
}