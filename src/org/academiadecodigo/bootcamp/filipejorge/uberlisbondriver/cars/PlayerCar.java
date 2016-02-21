package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;


/**
 * Created by filipejorge on 10/02/16.
 */
public class PlayerCar extends Car {

    private Color color;


    public PlayerCar(CarType carType) {
        super(carType);

        Representation rep = super.getRepresentation();
        rep.setColor(ColorUber.BLUE.getColor());
/*
        Picture pict = rep.getPicture();
        System.out.println("create player");
        for (int i = 0; i < pict.getWidth(); i++) {
            for (int j = 0; j < pict.getHeight(); j++) {
                pict.setColorAt(i, j, new Color(255-pict.getColorAt(i,j).getBlue(), 255-pict.getColorAt(i,j).getGreen(), 255-pict.getColorAt(i,j).getBlue()));

            }

        }*/

    }


    @Override
    public void acceleratePedal() {
        if (getGearShift() == -1 && getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
        }
        if ((getGearShift() == 1) && (getSpeed() < getMaxSpeed())) {
            setSpeed(getSpeed() + 1);
        }
        if (getSpeed() == 0) {
            setGearShift(1);
        }
    }


    @Override
    public void brake() {
        if (getGearShift() == 1 && getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
        }

        if ((getGearShift() == -1) && (getSpeed() < getMaxSpeed())) {
            setSpeed(getSpeed() + 1);
        }
        if (getSpeed() == 0) {
            setGearShift(-1);
        }
    }


}

