package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.RandomF;

public class Position {


    private float x;
    private float y;

    public Position(int xBoundMin, int yBoundMin, int xBoundMax, int yBoundMax) {
        this.x = RandomF.getRandom(xBoundMin, xBoundMax);
        this.y = RandomF.getRandom(yBoundMin, yBoundMax);
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(Position newPos) {
        this.x = newPos.x;
        this.y = newPos.y;
    }



  /*  public Position distanceTo(Position pos) {
        Position p = new Position();
        p.setPos(pos.getX() - this.getX(), pos.getY() - this.getY());
        return p;
    }*/

  /*  public Position directionTo(Position pos) { //TODO:Update to 360° direction
        Position p = new Position();
        int dirX = (int) Math.signum(pos.getX() - this.getX());
        int dirY = (int) Math.signum(pos.getY() - this.getY());
        p.setPos(dirX, dirY);
        return p;
    }*/

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
