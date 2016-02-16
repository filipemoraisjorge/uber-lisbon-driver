package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.PlayerCar;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.Driver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.PlayerDriver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Field;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.CarType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Game {

    public static final int MANUFACTURED_CARS = 1;

    public static final int MAXIMUM_CAR_SPEED = CarType.getAllMaxSpeed();

    /**
     * Graphical Car Field
     */
    Field field;

    /**
     * Container of Cars
     */
    Car[] cars;
    Driver[] drivers;
    /**
     * Animation delay
     */
    public static int delay;

    public Game(int delay) {

        Field.init();
        this.delay = delay;
    }

    /**
     * Creates a bunch of cars and randomly puts them in the field
     */
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        drivers = new Driver[MANUFACTURED_CARS];

        //Driver player = new PlayerDriver(new PlayerCar(CarType.UBERX));
        //KeyboardHandler carControlHandler = new CarKeybHandler(player.getCar());
        //MouseHandler carMouseHandler = new CarMouseHandler(player.getCar());
        //drivers[0] = player;
        System.out.println(drivers.length);
        for (int i = drivers.length - MANUFACTURED_CARS; i < drivers.length; i++) {
            //driver has a car

            drivers[i] = new Driver(CarFactory.getNewCar());
            drivers[i].getCar().getRepresentation().setInfoText(i + "");
        }
    }

    /**
     * Starts the animation
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {

        while (true) {

            // Pause for a while
            //Thread.sleep(delay);

            // Move all cars
            for (int i = 0; i < drivers.length; i++) {

                if (!drivers[i].getCar().isCrashed()) {
                    drivers[i].drive();

                    checkCollision(i);
                }
            }


        }
    }

    private void checkCollision(int i) {

        for (int j = 0; j < drivers.length; j++) {
            if (i == j) {
                continue;
            }

            if (drivers[i].getCar().checkCrashed(drivers[j].getCar())) {
                drivers[i].getCar().setCrashed(true);
                drivers[j].getCar().setCrashed(true);
            }

        }
    }
}