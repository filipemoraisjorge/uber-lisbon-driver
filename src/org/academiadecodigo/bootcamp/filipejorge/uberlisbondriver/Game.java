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
    public final int MAXLIVES = 5;
    public int lives = MAXLIVES;
    Driver[] drivers;
    boolean loseLive = false;
    long loseLiveTime;
    boolean inGameRoute = false;
    private GameMarker startMarker;
    private GameMarker endMarker;
    private boolean dead;
    private boolean inloseLiveTime;

    public Game(int delay) {

        Field.init();
        Game.delay = delay;
    }

    /**
     * Creates a bunch of cars and randomly puts them in the field
     */
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //first start
        startMarker = new GameMarker(GameMarker.MarkerType.START, 15);


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

        while (dead) {


            // Pause for a while
            Thread.sleep(delay);

            // Move all cars
            for (int i = 0; i < drivers.length; i++) {


                if (!drivers[i].getCar().isCrashed()) {
                    drivers[i].drive();


                }
                //only Player car checks collision. ie, the taxis dont collide with each other.
                checkCollision(0);
            }

            checkLives();

            Car playerCar = drivers[0].getCar();

            MarkersRoutine(playerCar);
//TODO: Show Lives
            //TODO Show inProtected Time.
            //TODO Score, quicker = more points.
            //TODO Taxis drivers Shouts
            //TODO Start Screen with instructions
            //TODO Drivers view?



        }

    }

    private void checkLives() {
        if (loseLive && !inloseLiveTime) {
            lives--;
            loseLiveTime = System.currentTimeMillis();
            loseLive = false;
            inloseLiveTime = true;
        }

        if (inloseLiveTime && (System.currentTimeMillis() - loseLiveTime > 5000)) {
            inloseLiveTime = false;
        }

        if (lives <= 0 && !inloseLiveTime) {
            dead = true;
        }
    }

    private void MarkersRoutine(Car playerCar) {

        if (startMarker != null && startMarker.isReached(playerCar)) {
            playerCar.getRepresentation().setInRoute(true);
            inGameRoute = true;
        }
        //if don't exits yet, it creates
        if (!inGameRoute && startMarker == null) {
            //new start
            startMarker = new GameMarker(GameMarker.MarkerType.START, 15);
        }
        //if do not exits yet, creates
        if (inGameRoute && endMarker == null) {
            endMarker = new GameMarker(GameMarker.MarkerType.END, 15);
        }

        //if exists, test if reached
        if (endMarker != null && endMarker.isReached(playerCar)) {
            startMarker.delete();
            endMarker.delete();
            startMarker = null;
            endMarker = null;
            playerCar.getRepresentation().setInRoute(false);
            inGameRoute = false;
        }
    }


    private void checkCollision(int i) {

        for (int j = 0; j < drivers.length; j++) {
            if (i == j) {
                continue;
            }

            if (drivers[i].getCar().checkCrashed(drivers[j].getCar())) {
                if (lives > 0 && !inloseLiveTime) {
                    loseLive = true;
                }

                if (lives <= 0) {
                    drivers[i].getCar().setCrashed(true);
                    drivers[j].getCar().setCrashed(true);
                }
            }

        }
    }
}