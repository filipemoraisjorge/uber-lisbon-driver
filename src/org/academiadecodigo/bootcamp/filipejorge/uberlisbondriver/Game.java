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

    public static final int MANUFACTURED_CARS = 2;

    public static final int MAXIMUM_CARS_SPEED = CarType.getAllMaxSpeed();


    /**
     * Animation delay
     */
    public static int delay;
    private final int MAX_LIVES = 5;
    Picture[] livesPictures = new Picture[MAX_LIVES];
    boolean loseLife = false;
    long loseLifeTime;
    boolean inGameRoute = false;
    int totalScore;
    long routeStartTime;
    int routeMaxScore;
    private Text scoreDraw;
    private Driver[] drivers;
    private GameMarker startMarker;
    private GameMarker endMarker;
    private int lives = MAX_LIVES;
    private boolean inLoseLifeTime;
    private boolean dead;
    private boolean spacePressed = false;
    //PlayCarInfo
    private Text playerCarSpeed;
    private Text playerCarAcc;
    private Text playerCarGear;
    private Text playerCarSteerAngle;

    private float startReverseAngle;

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
        //space = null;
        //System.gc();
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
        KeyboardHandler carControlHandler = new CarKeybHandler(player);
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

        //init scoreDraw
        scoreDraw = new Text(100, 243, "Score " + totalScore);
        scoreDraw.setColor(ColorUber.GREEN.getColor());
        scoreDraw.draw();

        //init playerCarInfo
        int infoX = 50;
        int incX = 25;
        playerCarSpeed = new Text(100, infoX = infoX + incX, "Speed " + drivers[0].getCar().getSpeed());
        playerCarAcc = new Text(100, infoX = infoX + incX, "Acc " + drivers[0].getCar().getAcceleration());
        playerCarGear = new Text(100, infoX = infoX + incX, "Gear " + drivers[0].getCar().getGearShift());
        playerCarSteerAngle = new Text(100, infoX = infoX + incX, "Steer " + drivers[0].getCar().getSteerAngle());
        playerCarSpeed.draw();
        playerCarAcc.draw();
        playerCarGear.draw();
        playerCarSteerAngle.draw();
        //start game cycle
        start();

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
                checkCollision(0);
                //only Player car checks collision. ie, the taxis don't collide with each other.
            }

            //update PlayerCarInfo
            playerCarSpeed.setText("Speed " + drivers[0].getCar().getSpeed());
            playerCarAcc.setText("Acc " + drivers[0].getCar().getAcceleration());
            playerCarGear.setText("Gear " + drivers[0].getCar().getGearShift());
            playerCarSteerAngle.setText("Steer " + drivers[0].getCar().getSteerAngle());


            checkLives();

            MarkersRoutine(drivers[0].getCar());

            //TODO Show inProtected Time.
            //TODO Taxis drivers Shouts
            //TODO Drivers view?
            //TODO Map editor


        }

    }

    private void initDrawLives() {
        Text livesInfo = new Text(50, 283, "Lives ");
        livesInfo.setColor(ColorUber.BLUE.getColor());
        livesInfo.draw();
        for (int i = 0; i < MAX_LIVES; i++) {
            livesPictures[i] = new Picture(90, 287, "resources/uber-lives.png");
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


    private void checkLives() {
        if (loseLife && !inLoseLifeTime) {
            lives--;
            drawLives();
            loseLifeTime = System.currentTimeMillis();
            loseLife = false;
            inLoseLifeTime = true;
        }

        if (inLoseLifeTime && (System.currentTimeMillis() - loseLifeTime > 5000)) {
            inLoseLifeTime = false;
        }

        if (lives <= 0 /*&& !inLoseLifeTime*/) {
            dead = true;
        }
    }

    private void MarkersRoutine(Car playerCar) {
        //If StartMarker don't exits yet, is created
        if (!inGameRoute && startMarker == null) {
            startMarker = new GameMarker(GameMarker.MarkerType.START, 30);
            }
        //Reached Start Marker
        if (startMarker != null && startMarker.isReached(playerCar)) {
            playerCar.getRepresentation().setInRoute(true);
            inGameRoute = true;

        }
        //If EndMarker do not exits yet, is created
        if (inGameRoute && endMarker == null) {
            int distance = 0;
            do {
                if (endMarker != null) {
                    endMarker.delete();
                }
                endMarker = new GameMarker(GameMarker.MarkerType.END, 30);
                distance = startMarker.distance(endMarker);
                System.out.println("new end " + distance);
            } while (distance <= 250);

            //start timing for the score
            routeMaxScore = distance;
            routeStartTime = System.currentTimeMillis();

        }

        //If EndMarker exists, test if is reached
        if (endMarker != null && endMarker.isReached(playerCar)) {
            //calculate score. quicker, better.
            int score = (int) Math.abs(routeMaxScore / ((routeStartTime - System.currentTimeMillis()) / 1000));
            totalScore += score;
            scoreDraw.setText("Score " + totalScore);

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
                if (lives > 0 && !inLoseLifeTime) {
                    loseLife = true;
                    startReverseAngle = drivers[i].getCar().getRepresentation().getVector().getDir().getAngle();
                    drivers[i].reversing();

                    //drivers[j].reversing();
                }

                if (lives <= 0) {
                    drivers[i].getCar().setCrashed(true);
                    //drivers[j].getCar().setCrashed(true);
                }
            }

        }
    }
}