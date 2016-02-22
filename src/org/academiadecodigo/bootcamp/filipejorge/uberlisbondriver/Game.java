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

    public static final int MANUFACTURED_CARS = 5;

    public static final int MAXIMUM_CARS_SPEED = CarType.getAllMaxSpeed();
    /**
     * Animation delay
     */
    public static int delay;
    Driver[] drivers;
    private GameMarker startMarker;
    private GameMarker endMarker;


    public Game(int delay) {

        Field.init();
        Game.delay = delay;
    }

    /**
     * Creates a bunch of cars and randomly puts them in the field
     */
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        startMarker = new GameMarker(GameMarker.MarkerType.START, 15);
        endMarker = new GameMarker(GameMarker.MarkerType.END, 15);

        drivers = new Driver[MANUFACTURED_CARS];

        Driver player = new PlayerDriver(new PlayerCar(CarType.UBERX));
        KeyboardHandler carControlHandler = new CarKeybHandler(player.getCar());
        MouseHandler carMouseHandler = new CarMouseHandler(player);
        drivers[0] = player;

        for (int i = 1 + drivers.length - MANUFACTURED_CARS; i < drivers.length; i++) {
            //driver has a car

            drivers[i] = new Driver(CarFactory.getNewCarbyType(CarType.TAXI));

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
            Thread.sleep(delay);

            // Move all cars
            for (int i = 0; i < drivers.length; i++) {



                    if (!drivers[i].getCar().isCrashed()) {
                        drivers[i].drive();

                        checkCollision(i);
                    }

            }

            Car playerCar = drivers[0].getCar();

            if(startMarker.isReached(playerCar)) {
                playerCar.getRepresentation().setInRoute(true);
                System.out.println("start");
            }
            if(endMarker.isReached(drivers[0].getCar())) {
                playerCar.getRepresentation().setInRoute(false);
                System.out.println("end");
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