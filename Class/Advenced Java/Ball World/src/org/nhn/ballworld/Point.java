package org.nhn.ballworld;

/**
 * 공의 위치
 */
public class Point {

    double x;
    double y;
    double length;
    double angle;


    public Point (double x, double y) {

        this.x = x;
        this.y = y;
    }

    public Point(Point anotherPoint) {
        this.x = anotherPoint.getX();
        this.y = anotherPoint.getY();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void moveX(double dx) {
        x += dx;
    }

    public void moveY(double dy) {
        y += dy;
    }

    // public double distanceFrom(Point point) {
    //     return Math.sqrt(Math.pow(x - point.getX(), 2));
    // }

    // public void move(double length, double angle) {

    //     this.length = length;
    //     this.angle = angle;

    // }

}


