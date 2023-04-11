package test;

import java.util.Random;
import org.nhn.ballworld.Ball;
import org.nhn.ballworld.MultiBallWorld;
import org.nhn.ballworld.Point;

public class TestMultiBallWorld {
    public static void main(String[] args) {

        MultiBallWorld ballWorld = new MultiBallWorld(500, 400);
        ballWorld.setVisible(true);

        Random random = new Random();
        for(int i = 0 ; i < 10 ; i++) {
            Point location = new Point(random.nextInt(ballWorld.getWidth()),
                    random.nextInt(ballWorld.getHeight()));
            double radius = 10.0 + random.nextInt(Math.min(ballWorld.getWidth(), ballWorld.getHeight()) / 10);

            ballWorld.add( new Ball(location, radius));
        }

        ballWorld.repaint();
    }

}
