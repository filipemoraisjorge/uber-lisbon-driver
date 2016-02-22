package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.PlayerCar;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.Driver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.PlayerDriver;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Field;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.CarType;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Game {

    public static final int MANUFACTURED_CARS = 5;

    public static final int MAXIMUM_CARS_SPEED = CarType.getAllMaxSpeed();


    /**
     * Animation delay
     */
    public static int delay;

    private final int MAXLIVES = 5;
    Picture[] livesPictures = new Picture[MAXLIVES];
    boolean loseLive = false;
    long loseLiveTime;
    boolean inGameRoute = false;
    private int lives = MAXLIVES;
    private Driver[] drivers;
    private GameMarker startMarker;
    private GameMarker endMarker;
    private boolean dead;
    private boolean inLoseLiveTime;
    private boolean spacePressed = false;


    public Game(int delay) {

        //  Field.init();
        Game.delay = delay;
    }

    public void splashScreen() {


        class SpaceKeybHandler implements KeyboardHandler {
            public SpaceKeybHandler() {
                //Keyboard
                Keyboard keyboard = new Keyboard(this);
                KeyboardEvent keyEventSpace = new KeyboardEvent();
                keyEventSpace.setKey(KeyboardEvent.KEY_SPACE);
                keyEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
                keyboard.addEventListener(keyEventSpace);
            }

            @Override
            public void keyPressed(KeyboardEvent keyboardEvent) {
                switch (keyboardEvent.getKey()) {
                    case KeyboardEvent.KEY_SPACE: {
                        spacePressed = true;
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyboardEvent keyboardEvent) {

            }
        }
        Rectangle background = new Rectangle(10, 10, 1398, 775);
        Picture splash = new Picture(10, 10, "resources/uber-splash.png");
        background.setColor(ColorUber.BLUE.getColor());


        background.fill();
        splash.draw();

        SpaceKeybHandler space = new SpaceKeybHandler();

        while (!spacePressed) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(spacePressed);
        }
        space = null;
        System.gc();
        splash.delete();
        //background.delete();
        init();


    }


    /**
     * Creates a bunch of cars and randomly puts them in the field
     */
    public void init() {
        Field.init();

        //first start marker
        startMarker = new GameMarker(GameMarker.MarkerType.START, 30);


        drivers = new Driver[MANUFACTURED_CARS];

        Driver player = new PlayerDriver(new PlayerCar(CarType.UBERX));
        KeyboardHandler carControlHandler = new CarKeybHandler(player.getCar());
        MouseHandler carMouseHandler = new CarMouseHandler(player);
        drivers[0] = player;

        for (int i = 1 + drivers.length - MANUFACTURED_CARS; i < drivers.length; i++) {
            //driver has a car

            try {
                drivers[i] = new Driver(CarFactory.getNewCarbyType(CarType.TAXI));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

        //show lives
        initDrawLives();

        //start game cycle
        start();

    }

    private void initDrawLives() {
        for (int i = 0; i < MAXLIVES; i++) {

            System.out.println("inside " + livesPictures[i] + " " + i);
            livesPictures[i] = new Picture(65, 287, "resources/uber-lives.png");
            livesPictures[i].translate((livesPictures[i].getWidth() * i) + (i * 5), 0);
            livesPictures[i].draw();
        }
    }

    private void drawLives() {
        for (int i = 0; i < lives + 1; i++) {
            livesPictures[i].delete();
        }
        for (int i = 0; i < lives; i++) {
            livesPictures[i].draw();
        }
    }

    /**
     * Starts the animation
     *
     * @throws InterruptedException
     */
    public void start() {
        while (!dead) {


            // Pause for a while
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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


            //TODO Score, quicker = more points.
            //TODO Show inProtected Time.
            //TODO Taxis drivers Shouts
            //TODO Start Screen with instructions
            //TODO Drivers view?


        }

    }

    private void checkLives() {
        if (loseLive && !inLoseLiveTime) {
            lives--;
            drawLives();
            loseLiveTime = System.currentTimeMillis();
            loseLive = false;
            inLoseLiveTime = true;
        }

        if (inLoseLiveTime && (System.currentTimeMillis() - loseLiveTime > 5000)) {
            inLoseLiveTime = false;
        }

        if (lives <= 0 /*&& !inLoseLiveTime*/) {
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
            startMarker = new GameMarker(GameMarker.MarkerType.START, 30);
        }
        //if do not exits yet, creates
        if (inGameRoute && endMarker == null) {
            endMarker = new GameMarker(GameMarker.MarkerType.END, 30);
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
                if (lives > 0 && !inLoseLiveTime) {
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