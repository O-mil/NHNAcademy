package org.nhn.ballworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * 공 그리기
 */
public class Ball {

    Point location; // 볼의 중심
    double radius;  //반지름은 double
    Color color;    // 색을 가짐
    double dx;
    double dy;

    /**
     * 검정색을 기본으로 생성.
     * @param location 볼 위치
     * @param radius2 볼 반지름
     */
    public Ball ( Point location, double radius2) {

        this.location = location;   //위치를 줌
        this.radius = radius2;       // 반지름을 줌
        this.color = Color.BLACK;   // 검정색을 기본으로 생성
    }

    /**
     * 지정된 색의 볼을 생성.
     * @param location 볼 위치
     * @param radius 볼 반지름
     * @param color 볼 색
     */
    public Ball (Point location, double radius, Color color) {

        this(location, radius);     // 위치와 반지름을 주면
        this.color = color;         // 색을 변경 할 수 있음
    }

    /**
     * 볼 위치.
     * @return
     */
    public Point getLocation() {
        return location;
    }

    /**
     * 볼 반지름.
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @param newColor
     */
    public void setColor (Color newColor) {

        color = newColor;
    }

    public Color getColor() {
        return color;
    }

    /**
     * 볼 그리기
     * @param graphics graphics
     */
    public void paint(Graphics graphics) {

        Color oldColor = graphics.getColor();
        graphics.setColor(color);
        graphics.fillOval((int) (location.getX() - getRadius()), (int) (location.getY() - getRadius()),
            (int) (getRadius() * 2), (int) (getRadius() * 2));
        graphics.setColor(oldColor);
    }

    public void setDisplacement(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        location.moveX(this.dx);
        location.moveY(this.dy);
    }

    public void isCollided(Rectangle rect) {
        Rectangle region = new Rectangle((int)(location.getX() - radius, radius * 2, radius * 2)), 0;

        rect.intersects(region);
    }
}
