package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;

/**
 * Created by filipejorge on 13/02/16.
 */
public class PlayerDriver extends Driver {
    Car car;

    public PlayerDriver(Car car) {
        super(car);
        this.car = car;
        super.setDirectionChangeRate(0);
        super.setSpeedChangeRate(0);
    }



}
