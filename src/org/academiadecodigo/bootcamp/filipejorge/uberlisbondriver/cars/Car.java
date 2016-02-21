package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.Game;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;

import java.io.FileNotFoundException;
import java.io.PrintStream;


abstract public class Car {


    private static final int MAXSTEERINGANGLE = 5;
    private static final float STEERINC = 1f;

    private CarType carType;
    private Representation representation;
    private int speed;
    private float steerAngle;
    private int gearShift; //-1 reverse; 0 Neutral; 1 Forward.
    private int maxSpeed;
    private Boolean crashed = false;


    private float turnAccumulator; //speed


    public Car(CarType carType) {

        this.carType = carType;
        this.speed = 0;
        this.steerAngle = 0;
        this.gearShift = 1;
        this.representation = new Representation(this);
        this.maxSpeed = this.carType.getMaxSpeed();

    }

    public CarType getCarType() {
        return carType;
    }

    public float getSteerAngle() {
        return steerAngle;
    }

    public void setSteerAngle(float steerAngle) {
        this.steerAngle = steerAngle;
    }

    public int getGearShift() {
        return gearShift;
    }

    public void setGearShift(int gearShift) {
        this.gearShift = gearShift;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public int getMAXSTEERINGANGLE() {
        return MAXSTEERINGANGLE;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isCrashed() {
        return this.crashed;
    }

    public void setCrashed(Boolean crashed) {
        this.crashed = crashed;
        this.representation.setCrashed();
        this.speed = 0;
        this.steerAngle = 0;
    }

    private void incTurnAccumulator(int speed) {
        this.turnAccumulator += (float) speed / Game.MAXIMUM_CARS_SPEED;
        // System.out.println(speed + " acc " + turnAccumulator);
    }


    public boolean isTurnToMove(int speed) {
        /** more speed = move more often
         * so until the accum doesn't get to 1 the car doesn't move.
         * the fastest car moves every turn.
         */

        if (turnAccumulator % (Game.MAXIMUM_CARS_SPEED) < 1) { //if is bellow 1 then it isn't your turn to move
            this.incTurnAccumulator(speed); //add a little more
            return false;
        } else {
            turnAccumulator = 0; //It's your time to move, lets reset the accumulator.
            return true;
        }
    }

    public void move() {

        if (!isCrashed() && isTurnToMove(speed) /*&& !checkBumper()*/) {
            // if not bumping into something, then move

            representation.move(speed, steerAngle, gearShift);


        }

    }

    public boolean checkBumper() {
        boolean warn1 = representation.isOnEdge();
        boolean warn2 = representation.getVector().isOutsideField();
        //boolean warn3 = isCrashed();
        return warn1 || warn2; //|| warn3;
    }


    public void acceleratePedal() {

        if (speed < this.maxSpeed) {

            speed++;

        }
    }

    public void brake() {

        if (speed > 0) {
            speed--;
        }
    }

    public void steeringWheel(SteerDirection steerDirection) {

        switch (steerDirection) {
            case LEFT:
                if (steerAngle > -MAXSTEERINGANGLE) {
                    steerAngle -= STEERINC;
                }
                break;
            case RIGHT:
                if (steerAngle < MAXSTEERINGANGLE) {
                    steerAngle += STEERINC;
                }
                break;
        }

    }

    public void invertShift() {
        gearShift = -gearShift;
    }


    public boolean checkCrashed(Car car) {
        return this.representation.collide(car.representation);
    }


    @Override
    public String toString() {
        return "Car{" +
                "carType=" + carType +
                ", representation=" + representation +
                ", speed=" + speed +
                ", steerAngle=" + steerAngle +
                ", gearShift=" + gearShift +
                ", crashed=" + crashed +
                '}';
    }

}
