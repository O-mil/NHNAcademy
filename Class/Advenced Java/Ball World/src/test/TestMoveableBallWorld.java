package test;

import org.nhn.ballworld.MultiBallWorld;
import org.nhn.ballworld.Ball;
import org.nhn.ballworld.Point;

public class TestMoveableBallWorld {
    public static void main(String[] args) {
        MultiBallWorld multiballWorld = new MultiBallWorld(500, 400);

        multiballWorld.setVisible(true);    // 창이 나오게 함

        Ball ball = new Ball (new Point(100.0, 100.0), 50.0);

        multiballWorld.add(ball);
        multiballWorld.run();   // 공이 하나 생김 import ball, point
    }
}
