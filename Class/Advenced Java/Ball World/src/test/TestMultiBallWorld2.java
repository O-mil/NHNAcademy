package test;

import java.util.Random;
import java.awt.Color;
import org.nhn.ballworld.Ball;
import org.nhn.ballworld.MultiBallWorld;
import org.nhn.ballworld.Point;


public class TestMultiBallWorld2 {
    public static void main(String[] args) {
        final Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.PINK};
        final MultiBallWorld ballWorld = new MultiBallWorld(500, 400);

        ballWorld.setVisible(true);

        Random random = new Random();
        for(int i = 0 ; i < 10 ; i++) {
            double radius = 10.0 + random.nextInt(Math.min(ballWorld.getWidth(), ballWorld.getHeight()) / 10);
            Point location = new Point(radius + random.nextInt((int) Math.round(ballWorld.getWidth() - 2 * radius)),
                    radius + random.nextInt((int) Math.round(ballWorld.getHeight() - 2 * radius)));

            int colorIndex = random.nextInt(colors.length * 2);
            if (colorIndex < colors.length) {
                ballWorld.add(new Ball(location, radius, colors[colorIndex]));
            } else {
                ballWorld.add(new Ball(location, radius));
            }
        }

        ballWorld.repaint();
    }
}