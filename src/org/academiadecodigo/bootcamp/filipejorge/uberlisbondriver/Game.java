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

    public static final int MAXIMUM_CARS_SPEED = CarType.getAllMaxSpeed();
    /**
     * Animation delay
     */
    public static int delay;
    /**
     * Graphical Car Field
     */
    Field field;
    /**
     * Container of Cars
     */
    Car[] cars;
    Driver[] drivers;

    public Game(int delay) {

        Field.init();
        this.delay = delay;
    }

    /**
     * Creates a bunch of cars and randomly puts them in the field
     */
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        drivers = new Driver[MANUFACTURED_CARS];

        Driver player = new PlayerDriver(new PlayerCar(CarType.UBERX));
        KeyboardHandler carControlHandler = new CarKeybHandler(player);
        MouseHandler carMouseHandler = new CarMouseHandler(player);
        drivers[0] = player;

        for (int i = 1 + drivers.length - MANUFACTURED_CARS; i < drivers.length; i++) {
            //driver has a car

            drivers[i] = new Driver(CarFactory.getNewCar());
            //drivers[i].getCar().getRepresentation().setInfoText(i + "");

        }
    }

    /**
     * Starts the animation
     *
     * @throws InterruptedException
     */
    public void start() {
        int[] turnArray = new int[MANUFACTURED_CARS];
        while (true) {

            // Pause for a while
/*            try {
                Thread.sleep(Game.delay);
            } catch (InterruptedException ex) {
                System.out.printf("thread interrupted exception");
            }*/
            // Move all cars
            for (int i = 0; i < drivers.length; i++) {
                if (!drivers[i].getCar().isCrashed()) {
                    if (turnArray[i] % (Game.MAXIMUM_CARS_SPEED) < 1) { //if is bellow 1 then it isn't your turn to move
                        turnArray[i] += (float) drivers[i].getCar().getSpeed() / Game.MAXIMUM_CARS_SPEED;
                    } else {
                        turnArray[i] = 0; //It's your time to move, lets reset the accumulator.
                    }

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