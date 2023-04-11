package org.nhnacademy.ballworld;

/**
 * BoundedWorld 클래스.
 */
public class BoundedWorld extends MovableBallWorld {

    Region left;
    Region right;
    Region top;
    Region bottom;

    public BoundedWorld(int width, int height) {
        super(width, height);
        left = new Region(-width, 0, width, height);
        right = new Region(width, 0, width, height);
        top = new Region(0, height, width, height);
        bottom = new Region(0, -height, width, height);
    }

    @Override
    public void next() {
        for(Ball ball : balls) {
            if (ball instanceof MovableBall) {
                MovableBall movableBall = (MovableBall)ball;

                movableBall.move();

                if (left.isCollision(movableBall.getRegion()) || right.isCollision(movableBall.getRegion())) {
                    movableBall.turnX();
                }

                if (top.isCollision(movableBall.getRegion()) || bottom.isCollision(movableBall.getRegion())) {
                    movableBall.turnY();
                }
            }
        }
    }
}
