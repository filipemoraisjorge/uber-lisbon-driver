package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.CarType;
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
    private float reverseAngle;
    private boolean accelerate;

    public Driver(Car car) {
        this.car = car;
        this.speedChangeRate = 50;
        this.directionChangeRate = 0.1f;
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

    public boolean isReversing() {
        return isReversing;
    }

    public void setReversing(boolean reversing) {
        isReversing = reversing;
    }

    public boolean isAccelerate() {
        return accelerate;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

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
        if (car.getSpeed() < car.getMaxSpeed()) {
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
        if (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.LEFT);
        }
    }

    public void steerRight() {
        if (Math.abs(car.getSteerAngle()) < car.getMAXSTEERINGANGLE()) {
            car.steeringWheel(SteerDirection.RIGHT);
        }
    }

    public void steerMiddle() {
        while (Math.abs(car.getSteerAngle()) != 0) {
            if (car.getSteerAngle() > 0) {
                car.steeringWheel(SteerDirection.LEFT);
            } else {
                car.steeringWheel(SteerDirection.RIGHT);
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

        if ((referenceAngle - car.getRepresentation().getVector().getDir().getAngle() < degrees) && !car.checkBumper()) {
            steerLeft();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnRight(int degrees) {

        float referenceAngle = car.getRepresentation().getVector().getDir().getAngle();

        if ((car.getRepresentation().getVector().getDir().getAngle() - referenceAngle < degrees) && !car.checkBumper()) {
            steerRight();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnLeftTime(int milliSeconds) {


        long startTime = System.currentTimeMillis();


        if ((System.currentTimeMillis() - startTime < milliSeconds) && !car.checkBumper()) {
            car.setSteerAngle(-5);
            steerLeft();
            accelerate();
        }
        stop();
        steerMiddle();
    }

    public void turnRightTime(int milliSeconds) {

        long startTime = System.currentTimeMillis();

        if ((System.currentTimeMillis() - startTime < milliSeconds) && !car.checkBumper()) {
            car.setSteerAngle(5);
            //steerRight();
            //accelerate();

        }
        stop();
        steerMiddle();
    }

    public void reversing() {



        if (!isReversing) { //init routine
            isReversing = true;
            reverseAngle = Math.abs(car.getRepresentation().getVector().getDir().getAngle());
            //calc the angle to wall. Need it to decide which side should I steeringWheel to.
            //inverse steeringWheel to max
            car.setSteerAngle(((reverseAngle % 90) <= 45 ? -car.getMAXSTEERINGANGLE() : car.getMAXSTEERINGANGLE()));
            car.setGearShift(-1);    //changeShift();
            car.setAcceleration(car.MAXACCELERATION);
        }

        //reverseAngle = (reverseAngle + Math.abs(car.getSteerAngle()));
        if (car.getCarType() == CarType.UBERX) {
            System.out.println("reverseangle " + reverseAngle );
        }

        if (isReversing && car.getRepresentation().getVector().isOutsideField() /*|| reverseAngle >= 20)*/) { //end routine
            reverseAngle = 0;
            car.handBrake();
            car.setGearShift(1);
            car.setSteerAngle(0);
            car.setAcceleration(car.MAXACCELERATION);
            isReversing = false;
            if (car.getCarType() == CarType.UBERX) {
                //System.out.println("------------outside----------------------");
            }
        }

        if (car.getCarType() == CarType.UBERX) {
            System.out.println("actual " + Math.abs(car.getRepresentation().getVector().getDir().getAngle()));
            System.out.println("----------------------------------");
        }
        if (isReversing && reverseAngle-car.getRepresentation().getVector().getDir().getAngle() >= 90) { //end routine
            System.out.println("parou!");
            /* reverseAngle = 0;
            car.handBrake();
            car.setGearShift(1);
            car.setSteerAngle(0);
            car.setAcceleration(car.MAXACCELERATION);
            isReversing = false;*/
            }


    }


    public void drive() {

        if (car.checkBumper()) {
            //if hits something it starts reversing
            reversing();
        }
        //moveForward();
        car.move();

        //For AI cars. Decide to change speed and direction
        if (!isReversing) {
            int random = (int) (Math.random() * 100);
            if (random < directionChangeRate) {
                decideChangeDirection();
            }
            if (random < speedChangeRate) {
                decideChangeSpeed();
            }
        }
    }


    public void decideChangeSpeed() {
        switch ((int) Math.abs(Math.random())) {
            case 0:
                car.setAcceleration(0.1f + (float) Math.random() * 5);
                break;
            default:
                car.setAcceleration(-(0.1f + (float) Math.random() * 5));
        }
    }


    public void decideChangeDirection() {
        switch (turnCounter % 10) {
            case 0:
                car.setSteerAngle(0.5f + (float) (Math.random() * car.getMAXSTEERINGANGLE()));
                turnCounter++;
                break;
            case 1:
                car.setSteerAngle(-(0.5f + (float) (Math.random() * car.getMAXSTEERINGANGLE())));
                turnCounter++;
                break;
            default:
                car.setSteerAngle(0);
                turnCounter++;
        }

    }

}
