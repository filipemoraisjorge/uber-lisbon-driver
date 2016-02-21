package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.SteerDirection;

/**
 * Created by filipejorge on 12/02/16.
 * <p>
 * Has a Car.
 * Uses the Cars Methods to drive it.
 * Maneuvers the car.
 */
public class Driver {

    private Car car;

    private float speedChangeRate;
    private float directionChangeRate;

    private int turnCounter;

    private boolean isReversing;

    public Driver(Car car) {
        this.car = car;
        this.speedChangeRate = 50;
        this.directionChangeRate = 0.2f;
    }

    public Car getCar() {
        return car;
    }

    public void setDirectionChangeRate(float directionChangeRate) {
        this.directionChangeRate = directionChangeRate;
    }

    public void setSpeedChangeRate(float speedChangeRate) {
        this.speedChangeRate = speedChangeRate;
    }

    //basic operations, with car.move()(refresh animation) in it

    public void changeShift() {
        car.invertShift();
    }

    public void pressAccelerate() {
        car.acceleratePedal();
    }

    public void brakePedal() {
        car.brake();
    }


    public void accelerate() {
        while (car.getSpeed() < car.getMaxSpeed()) {
            car.acceleratePedal();
        }
        // car.move();

    }

    public void stop() {
        while (car.getSpeed() > 0) {
            car.brake();
        }
        // car.move();

    }

    public void steerLeft() {
        while (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.LEFT);
//            car.move();
        }
    }

    public void steerRight() {
        while (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.RIGHT);
            //           car.move();
        }
    }

    public void steerMiddle() {
        while (Math.abs(car.getSteerAngle()) != 0) {
            if (car.getSteerAngle() > 0) {
                car.steeringWheel(SteerDirection.LEFT);
                //             car.move();
            } else {
                car.steeringWheel(SteerDirection.RIGHT);
                //           car.move();
            }
        }

    }


    //Driver's maneuvers

    public void moveForward() {
        car.setGearShift(1);
        accelerate();
    }

    public void moveBackwards() {
        car.setGearShift(-1);
        accelerate();
    }


    public void moveForward(int distance) {
        int distanceCount = 0;
        while (distanceCount < distance) {
            moveForward();
            distanceCount++; //TODO: turn in to real distance in pixels.
        }
        stop();
    }

    public void moveBackwards(int distance) {
        int distanceCount = 0;
        while (distanceCount < distance) {
            moveBackwards();
            distanceCount++; //TODO: turn in to real distance in pixels.
        }
        stop();
    }

    public void turnLeft(int degrees) {

        float referenceAngle = car.getRepresentation().getVector().getDir().getAngle();

        while ((referenceAngle - car.getRepresentation().getVector().getDir().getAngle() < degrees) && !car.checkBumper()) {
            steerLeft();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnRight(int degrees) {

        float referenceAngle = car.getRepresentation().getVector().getDir().getAngle();

        while ((car.getRepresentation().getVector().getDir().getAngle() - referenceAngle < degrees) && !car.checkBumper()) {
            steerRight();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnLeftTime(int milliSeconds) {


        long startTime = System.currentTimeMillis();


        while ((System.currentTimeMillis() - startTime < milliSeconds) && !car.checkBumper()) {
            car.setSteerAngle(-5);
            //steerLeft();
            //accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnRightTime(int milliSeconds) {

        long startTime = System.currentTimeMillis();

        while ((System.currentTimeMillis() - startTime < milliSeconds) && !car.checkBumper()) {
            car.setSteerAngle(5);
            //steerRight();
            //accelerate();

        }
        stop();
        steerMiddle();
    }

    public void reversing() {

        if (!isReversing && car.getRepresentation().isOnEdge()) { //init routine
            isReversing = true;
            float wallAngle = car.getRepresentation().getVector().getDir().getAngle() % 360; //HORRIVEL!
            //calc the angle to wall. Need it to decide which side should I steeringWheel to.
            //inverse steeringWheel to max
            car.setSteerAngle(((wallAngle % 90) <= 45 ? -car.getMAXSTEERINGANGLE() : car.getMAXSTEERINGANGLE()));
            car.setGearShift(-1);    //changeShift();
        }

        if (isReversing) { //the during routine
            car.setSpeed(car.getSpeed());
        }

        if (isReversing && car.getRepresentation().getVector().isOutsideField()) { //end routine
            steerMiddle();
            changeShift();
            pressAccelerate();
            isReversing = false;
        }
    }


    public void drive() {

        if (car.checkBumper()) {
            //if hits something it starts reversing
            reversing();
        }
        //moveForward();
        car.move();

        decideChangeDirection();
        decideChangeSpeed();


    }


    public void decideChangeSpeed() {
        double random = (Math.random() * 100);
        if (random < speedChangeRate) {
            switch ((int) Math.random()) {
                case 0:
                    car.acceleratePedal();
                    break;
                default:
                    car.brake();
            }
        }
    }


    public void decideChangeDirection() {
//TODO:not working..
        double random = (Math.random() * 100);
        if (random < directionChangeRate) {
            switch (turnCounter % 2) {
                case 0:

                    turnRightTime(1 + ((int) Math.random() * 1));
                    turnCounter++;
                    break;
                case 1:

                    turnLeftTime(1 + ((int) Math.random() * 1));
                    turnCounter++;
                    break;
            }
        }
    }

}
