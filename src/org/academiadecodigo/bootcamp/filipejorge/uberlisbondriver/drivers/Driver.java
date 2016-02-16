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

    private float directionSpeedRate;
    private float directionChangeRate;

    private int turnCounter;


    public Driver(Car car) {
        this.car = car;
        this.directionSpeedRate = 50;
        this.directionChangeRate = 0.2f;
    }

    public Car getCar() {
        return car;
    }

    public void setDirectionChangeRate(float directionChangeRate) {
        this.directionChangeRate = directionChangeRate;
    }

    public void setDirectionSpeedRate(float directionSpeedRate) {
        this.directionSpeedRate = directionSpeedRate;
    }

    //basic operations, with car.move()(refresh animation) in it

    public void changeShift() {
        car.setGearShift((-car.getGearShift()));
    }

    public void pressAccelerate() {
        car.acceleratePedal();
    }

    public void brakePedal() {
        car.brakePedal();
    }



    public void accelerate() {
        while (car.getSpeed() < car.getMaxSpeed()) {
            car.acceleratePedal();
        }

        car.move();
    }

    public void stop() {
        while (car.getSpeed() > 0) {
            car.brakePedal();
        }

        car.move();
    }

    public void steerLeft() {
        while (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.LEFT);
        }
        car.move();
    }

    public void steerRight() {
        while (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.RIGHT);
        }
        car.move();
    }

    public void steerMiddle() {
        while (Math.abs(car.getSteerAngle()) != 0) {
            if (car.getSteerAngle() > 0) {
                car.steeringWheel(SteerDirection.LEFT);
            } else {
                car.steeringWheel(SteerDirection.RIGHT);
            }
        }
        car.move();
    }


    //Driver's maneuvers

    public void moveForward() throws InterruptedException {
        car.setGearShift(1);
        accelerate();
    }

    public void moveBackwards() throws InterruptedException {
        car.setGearShift(-1);
        accelerate();
    }


    public void moveForward(int distance) throws InterruptedException {
        int distanceCount = 0;
        while (distanceCount < distance) {
            moveForward();
            distanceCount++; //TODO: turn in to real distance in pixels.
        }
        stop();
    }

    public void moveBackwards(int distance) throws InterruptedException {
        int distanceCount = 0;
        while (distanceCount < distance) {
            moveBackwards();
            distanceCount++; //TODO: turn in to real distance in pixels.
        }
        stop();
    }

    public void turnLeft(int degrees) throws InterruptedException {

        float referenceAngle = car.getRepresentation().getVector().getDir().getAngle();

        while ((referenceAngle - car.getRepresentation().getVector().getDir().getAngle() < degrees) && !car.checkBumper()) {
            steerLeft();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnRight(int degrees) throws InterruptedException {

        float referenceAngle = car.getRepresentation().getVector().getDir().getAngle();

        while ((car.getRepresentation().getVector().getDir().getAngle() - referenceAngle < degrees) && !car.checkBumper()) {
            steerRight();
            accelerate();
        }
        stop();
        steerMiddle();
    }
/*
public void reversing() throws InterruptedException {
        //stop();

        while(car.getRepresentation().getVector().isOutsideField()) {
            steerMiddle();
            moveBackwards();
        }

        //calc the angle to wall. Need it to decide which side should I steeringWheel to.
        float wallAngle = car.getRepresentation().getVector().getDir().getAngle() % 360; //HORRIVEL!
        //inverse steeringWheel to max
        car.setSteerAngle(((wallAngle % 90) <= 45 ? -car.getMAXSTEERINGANGLE() : car.getMAXSTEERINGANGLE()));
        //moveBackwards(10);
        while (*//*!car.checkBumper()*//* !car.getRepresentation().getVector().isOutsideField()) { //TODO: another way? only works when on the edge
            moveBackwards();
        }
        steerMiddle();
        // stop();
        moveForward();
    }
    */
    public void reversing() throws InterruptedException {
/*        brakePedal();
        brakePedal();
        brakePedal();
        changeShift();
        accelerate();*/

        //calc the angle to wall. Need it to decide which side should I steeringWheel to.
        float wallAngle = car.getRepresentation().getVector().getDir().getAngle() % 360; //HORRIVEL!
        //inverse steeringWheel to max
        car.setSteerAngle(((wallAngle % 90) <= 45 ? -car.getMAXSTEERINGANGLE() : car.getMAXSTEERINGANGLE()));
        //moveBackwards(10);
        while (/*!car.checkBumper()*/ !car.getRepresentation().getVector().isOutsideField()) { //TODO: another way? only works when on the edge
            moveBackwards();
        }
        steerMiddle();
        // stop();
        moveForward();
    }


    public void drive() throws InterruptedException {

        if (car.checkBumper()) {
            //if hits something it starts reversing
            reversing();
        }
        moveForward();

        decideChangeDirection();
        decideChangeSpeed();


    }


    public void decideChangeSpeed() {
        double random = (Math.random() * 100);
        if (random < directionSpeedRate) {
            switch ((int) Math.random()) {
                case 0:
                    car.acceleratePedal();
                    break;
                default:
                    car.brakePedal();
            }
        }
    }


    public void decideChangeDirection() throws InterruptedException {

        double random = (Math.random() * 100);
        if (random < directionChangeRate) {
            switch (turnCounter % 2) {
                case 0:

                    turnRight((int) (Math.random() * 180));
                    turnCounter++;
                    break;
                case 1:

                    turnLeft((int) (Math.random() * 180));
                    turnCounter++;
                    break;
            }
        }
    }

}
