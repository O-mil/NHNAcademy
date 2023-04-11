package org.nhnacademy.ballworld;

public class Motion {

    double dx;
    double dy;
    double velocity;
    double angle;

    // 이동량 0으로 생성
    public Motion() {

        this.dx = 0;
        this.dy = 0;
        this.velocity = 0;
        this.angle = 0;
    }

    /**
     * 속도와 각도 정보로 motion 생성
     * @param velocity 속도
     * @param angle 각도
     */
    public void Motion(double velocity, double angle) {

        this.velocity = velocity;
        this.angle = angle;

        velocity = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        angle = Math.toDegrees(Math.asin(dy / this.velocity));

    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void addVelocity(double velocity) {

    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void addAngle(double angle) {

    }

    public void setDXDY(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
        double radian = Math.toRadians(angle);

        dx = velocity * Math.cos(radian);
        dy = velocity * Math.sin(radian);
    }

    public double getDX() {
        return dx;
    }

    public void setDX(double dx) {
        this.dx = dx;
    }

    public void addDX(double dx) {
        dx += dx;
    }

    public void turnX() {
        dx -= dx;
    }

    public double getDY() {
        return dy;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

    public void addDY(double dy) {
        dy += dy;
    }

    public void turnY() {
        dy -= dy;
    }
}