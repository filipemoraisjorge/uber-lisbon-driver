package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.RandomF;

/**
 * Created by filipejorge on 03/02/16.
 */
public class Direction {
    // given 360° degree/2π rad  calculate (x,y) normal values
    // 0° 0π = (1,0) RIGHT
    // 90° π/2 = (0,1) BOTTOM
    // 180° π = (-1,0) LEFT
    // 270° 3π/2 = (0,-1) UP

  /*
    TOPRIGHT(-1, -1),
    TOP(0, -1), -90, 270
    UPLEFT(1, -1),
    RIGHT(1, 0), 0
    DOWNLEFT(1, 1),
    DOWN(0, 1), 90
    DOWNRIGHT(-1, 1),
    LEFT(-1, 0); 180 -0
  */

    private float xDir;
    private float yDir;
    private float angle;
    private float angleRad;

    public Direction() {
        this.angle = RandomF.getRandom(0, 360);
        this.angleRad = (float) Math.toRadians(angle);
        this.xDir = (float) Math.cos(angleRad);
        this.yDir = (float) Math.sin(angleRad);
    }

    public Direction(float xDir, float yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
        this.angleRad = (float) Math.atan(yDir / xDir); //θ = tan-1 ( y / x )
        this.angle = (int) Math.toDegrees(angleRad);
    }

    public Direction(int angle) {
        this.angle = angle;
        this.angleRad = (float) Math.toRadians(angle);
        this.xDir = (float) Math.cos(angleRad);
        this.yDir = (float) Math.sin(angleRad);
    }

    public static Direction getRandomDir() {
        return new Direction(RandomF.getRandomFloat(-1, 1), RandomF.getRandomFloat(-1, 1));
    }

    public float getxDir() {
        return xDir;
    }

    public float getyDir() {
        return yDir;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        this.angleRad = (float) Math.toRadians(angle);
        this.xDir = (float) Math.cos(angleRad);
        this.yDir = (float) Math.sin(angleRad);
    }

    public void setDirection(float xDir, float yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public Direction getOppositeDirection() {
        float opoX = this.xDir * -1;
        float opoY = this.yDir * -1;
        Direction dir = new Direction(opoX, opoY);
        return dir;
    }

    public boolean isOppositeDirection(Direction dir) { //TODO:test if is working
        float opoCol = this.xDir * -1;
        float opoRow = this.yDir * -1;
        return (dir.xDir == opoCol) && (dir.yDir == opoRow);
    }

    public void rotateDir(float angle) { //angle between 0° and 359°
        this.angle = this.angle + angle;

        this.angleRad = (float) Math.toRadians(this.angle);
        this.xDir = (float) Math.cos(angleRad);
        this.yDir = (float) Math.sin(angleRad);

    }

    public boolean equals(Direction dir) {
        return ((dir.xDir == this.xDir) && (dir.yDir == this.yDir));

    }

    @Override
    public String toString() {
        return "Direction{" +
                "xDir=" + xDir +
                ", yDir=" + yDir +
                ", angle=" + angle +
                '}';
    }
}
