package org.nhn.ballworld;

import java.awt.Color;


public class MovableBall extends Ball {

    Point oldLocation;
    double distance;
    double angle;

    public MovableBall(Point location, double radius2) {
        super(location, radius2);
    }

    public MovableBall (Point location, double radius, Color color) {
        super(location, radius, color);
    }

    public void setDisplacement(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }

    public void move(double distance, double angle) {
        setDisplacement(distance, angle);
        move();
    }

    public void move() {
        oldLocation = new Point(location);
        location.move(distance, angle);
    }

    public void moveRandom() {
        oldLocation = new Point(location);
        location.move(Math.random() * distance, Math.random() * angle);
    }

    public void moveBack() {
        location = oldLocation;
    }
}
