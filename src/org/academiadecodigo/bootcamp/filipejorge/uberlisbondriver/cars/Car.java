package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;


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

    public void move() {

        if (!isCrashed() /*&& !checkBumper()*/) {
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

    public void brakePedal() {
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

    public void inverteShift() {
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
