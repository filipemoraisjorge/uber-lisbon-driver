package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.Game;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;


abstract public class Car {


    public static final float FRICTION = 0.05f;
    private static final int MAXSTEERINGANGLE = 5;
    private static final float STEERINC = 1f;

    private CarType carType;
    private Representation representation;
    private float speed;
    private float acceleration;
    private int mass;
    private float steerAngle;
    private int gearShift; //-1 reverse; 0 Neutral; 1 Forward.
    private int maxSpeed;
    private Boolean crashed = false;

    private float turnAccumulator; //speed


    public Car(CarType carType) {

        this.carType = carType;
        this.speed = 0;
        this.acceleration = 0;
        this.mass = 1;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
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
        this.acceleration = 0;
        this.steerAngle = 0;
    }


    private void incTurnAccumulator(float speed) {
        this.turnAccumulator += (float) speed / Game.MAXIMUM_CARS_SPEED;
        // System.out.println(speed + " accelerate " + turnAccumulator);
    }


    public boolean isTurnToMove(float speed) {
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
        //its like a car's engine.

        //calculate forces
        //apply friction to car, it reduces speed.

        // apply acceleration to speed.

        if (speed > 0) {
            speed -= FRICTION;
        }

        speed = speed + (mass * acceleration);

        if (speed < 0) {
            System.out.println(speed);
            speed = 0;
        }
        if (speed > this.maxSpeed) {
            speed = this.maxSpeed;
        }


        //Move if it is his turn.
        if (!isCrashed() && isTurnToMove(speed)) {
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
        if (acceleration < 5) {
            acceleration = acceleration + 0.1f;
        }
    }

    public void brake() {
        if (acceleration > -5) {
            acceleration = acceleration - 0.5f;
        }
    }


    public void handBrake() {
        //quick brake.
        acceleration = -5;

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
