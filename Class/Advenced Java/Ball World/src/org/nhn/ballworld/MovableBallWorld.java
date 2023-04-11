package org.nhn.ballworld;

/**
 *
 */
public class MovableBallWorld extends MultiBallWorld {

    /**
     *
     * @param width
     * @param height
     */
    public MovableBallWorld(int width, int height)  {

        super(width, height);
        setTitle("Movable Ball World");
        setSize(width, height);
    }

    /**
     * 단위 시간만큼 이동.
     */
    public void step() {

    }

    /**
     *
     * @param seconds
     * @throws InterruptedException
     */
  	public void run(int seconds) throws InterruptedException {

    }
}