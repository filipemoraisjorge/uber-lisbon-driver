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

    public static final int MANUFACTURED_CARS = 1;

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
    int totalScore;
    long routeStartTime;
    int routeMaxScore;
    private Text scoreDraw;
    private Driver[] drivers;
    private GameMarker startMarker;
    private GameMarker endMarker;
    private int lives = MAXLIVES;
    private boolean inLoseLiveTime;
    private boolean dead;
    private boolean spacePressed = false;
    //PlayCarInfo
    private Text playerCarSpeed;
    private Text playerCarAcc;
    private Text playerCarGear;
    private Text playerCarSteerAngle;

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
                //only Player car checks collision. ie, the taxis dont collide with each other.
                checkCollision(0);
            }

            //update PlayerCarInfo
            //playerCarSpeed.delete();
            playerCarSpeed.setText("Speed " + drivers[0].getCar().getSpeed());
            //playerCarSpeed.draw();
            //playerCarAcc.delete();
            playerCarAcc.setText("Acc " + drivers[0].getCar().getAcceleration());
            //playerCarAcc.draw();
            //playerCarGear.delete();
            playerCarGear.setText("Gear " + drivers[0].getCar().getGearShift());
            //playerCarGear.draw();
            //playerCarSteerAngle.delete();
            playerCarSteerAngle.setText("Steer " + drivers[0].getCar().getSteerAngle());
            //playerCarSteerAngle.draw();


            checkLives();

            MarkersRoutine(drivers[0].getCar());

            //TODO Show inProtected Time.
            //TODO Taxis drivers Shouts
            //TODO Drivers view?


        }

    }

    private void initDrawLives() {
        Text livesInfo = new Text(50, 283, "Lives ");
        livesInfo.setColor(ColorUber.BLUE.getColor());
        livesInfo.draw();
        for (int i = 0; i < MAXLIVES; i++) {
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
        //Reached Start Marker
        if (startMarker != null && startMarker.isReached(playerCar)) {
            playerCar.getRepresentation().setInRoute(true);
            inGameRoute = true;

        }
        //If StartMarker don't exits yet, is created
        if (!inGameRoute && startMarker == null) {
            startMarker = new GameMarker(GameMarker.MarkerType.START, 30);
        }
        //If EndMarker do not exits yet, is created
        if (inGameRoute && endMarker == null) {
            endMarker = new GameMarker(GameMarker.MarkerType.END, 30);
            //start timing for the score
            routeMaxScore = startMarker.distance(endMarker);
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
                System.out.println("crash!!!!!!");
                if (lives > 0 && !inLoseLiveTime) {
                    loseLive = true;
                    drivers[i].reversing();
                    drivers[j].reversing();
                }

                if (lives <= 0) {
                    drivers[i].getCar().setCrashed(true);
                    drivers[j].getCar().setCrashed(true);
                }
            }

        }
    }
}