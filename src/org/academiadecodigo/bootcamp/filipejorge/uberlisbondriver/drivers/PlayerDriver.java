package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;

/**
 * Created by filipejorge on 13/02/16.
 */
public class PlayerDriver extends Driver {
    public PlayerDriver(Car car) {
        super(car);
        super.setDirectionChangeRate(0);
        super.setDirectionSpeedRate(0);
    }


}
