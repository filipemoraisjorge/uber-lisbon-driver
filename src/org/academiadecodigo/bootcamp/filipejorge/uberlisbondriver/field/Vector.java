package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;


import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.Game; //because Vector needs to know the delay

/**
 * Created by filipejorge on 10/02/16.
 * <p>
 * Euclidean vector
 * <p>
 * knows were it is and where is it going. (and draws itself to canvas)
 * <p>
 * has a Initial Point: Position pos
 * and a direction: (self explanatory) Direction dir
 * <p>
 * for now is magnitude is 1, works like a Norm Vector
 * if he had magnitude it would be easy to represent speed, for example.
 */
public class Vector {

    private static int xBoundMin;//= MARGIN+1;
    private static int yBoundMin;//= MARGIN+1;
    private static int xBoundMax;//= (Field.width - 1)-1;
    private static int yBoundMax;//= (Field.height - 1)-1;
    private Position pos;
    private Direction dir;
    private Position lastPos;
    private float turnAccumulator;

    public Vector() {

        Vector.validBoundaries(xBoundMin, yBoundMin, xBoundMax, yBoundMax);
        //Vector.validBoundaries(xBoundMin, yBoundMin, xBoundMax - (width), yBoundMax - (height));


        this.pos = new Position(xBoundMin, yBoundMin, xBoundMax, yBoundMax);
        this.dir = new Direction();
        this.lastPos = new Position(xBoundMin, yBoundMin, xBoundMax, yBoundMax);
        this.lastPos.setPos(this.pos);
    }

    public static void validBoundaries(int xIni, int yIni, int xEnd, int yEnd) {
        xBoundMin = xIni;
        yBoundMin = yIni;
        xBoundMax = xEnd;
        yBoundMax = yEnd;
    }

    public Position getPos() {
        return pos;
    }

    public Direction getDir() {
        return dir;
    }

    public Position getLastPos() {
        return lastPos;
    }

    public boolean isOutsideField() {
        return ((pos.getX() < xBoundMin) || (pos.getX() > xBoundMax) || (pos.getY() < yBoundMin) || (pos.getY() > yBoundMax)); //pos
    }

    public boolean isOnEdge() {
        float futureX = pos.getX() + dir.getxDir();
        float futureY = pos.getY() + dir.getyDir();
        return ((futureX < xBoundMin) || (futureX > xBoundMax) || (futureY < yBoundMin) || (futureY > yBoundMax)); //pos
    }

    private void incTurnAccumulator(int speed) {
        this.turnAccumulator += (float) speed / Game.MAXIMUM_CAR_SPEED;

    }

    public boolean isTurnToMove(int speed) {
        /** more speed = move more often
         * so until the accum doesn't get to 1 the car doesn't move.
         * the fastest car moves every turn.
         */

        if (turnAccumulator % (Game.MAXIMUM_CAR_SPEED) < 1) { //if is bellow 1 then it isn't your turn to move
            this.incTurnAccumulator(speed); //add a little more
            return false;
        } else {
            turnAccumulator = 0; //It's your time to move, lets reset the accumulator.
            return true;
        }
    }

    public void move(int speed, float steerAngle, int shift) {

        //save lastPos
        lastPos.setPos(this.pos);

        dir.rotateDir(steerAngle);


        //move pos towards dir
        pos.setPos(pos.getX() + (shift * dir.getxDir()), pos.getY() + (shift * dir.getyDir()));
        //}
    }


    @Override
    public String toString() {
        return "Vector{" +
                "pos=" + pos +
                ", dir=" + dir +
                '}';
    }
}
