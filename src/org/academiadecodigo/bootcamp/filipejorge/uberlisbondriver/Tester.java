package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.CarType;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.PlayerCar;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.UberBlack;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.Driver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.PlayerDriver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Field;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * Created by filipejorge on 12/02/16.
 */
public class Tester {
    Field field;

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        Field.init();
        start();
    }

    public static void start() throws InterruptedException {
        Driver driver = new PlayerDriver(new PlayerCar(CarType.UBERX));
        KeyboardHandler carControlHandler = new CarKeybHandler(driver.getCar());
        MouseHandler carMouseHandler = new CarMouseHandler(driver);

        Game.delay = 20;

        while (true) {

            // Pause for a while
            // Thread.sleep(1000);

       /*     driver.moveForward();
            driver.turnRight(90);
            driver.moveForward(150);
            driver.moveBackwards();
            driver.turnLeft(180);
            driver.moveBackwards(250);
*/

            //driver.reversing();

            driver.drive();


        }


    }
}

