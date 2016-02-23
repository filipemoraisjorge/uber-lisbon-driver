package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;
import org.academiadecodigo.simplegraphics.graphics.Color;


/**
 * Created by filipejorge on 10/02/16.
 */
public class PlayerCar extends Car {

    private Color color;


    public PlayerCar(CarType carType) {
        super(carType);

        Representation rep = super.getRepresentation();
        rep.setColor(ColorUber.BLUE.getColor());
    }


   @Override
    public void acceleratePedal() {

        if (isAccelerate() && getGearShift() == -1 && getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
        }
        if (isAccelerate() && getGearShift() == 1 && getSpeed() < getMaxSpeed()) {
            setSpeed(getSpeed() + 1);
        }
        if (getSpeed() == 0) {
            setGearShift(1);
        }
    }

    @Override
    public void deacceleratePedal() {

        if (!isAccelerate() && getGearShift() == 1 && getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
        }

        if (!isAccelerate() && getGearShift() == -1 && getSpeed() < getMaxSpeed()) {
            setSpeed(getSpeed() + 1);
        }
    }

    @Override
    public void brake() {
        if (!isAccelerate() && getGearShift() == 1 && getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
        }

        if (!isAccelerate() && getGearShift() == -1 && getSpeed() < getMaxSpeed()) {
            setSpeed(getSpeed() + 1);
        }
        if (getSpeed() == 0) {
            setGearShift(-1);
        }
    }


}

