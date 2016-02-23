package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.SteerDirection;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.drivers.Driver;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by filipejorge on 10/02/16.
 */
public class CarKeybHandler implements KeyboardHandler {

    //the Car to be controlled;
    private Car car;
    private Driver driver;

    //Keyboard
    private Keyboard keyboard = new Keyboard(this);


    CarKeybHandler(Driver driver) {
        this.driver = driver;
        this.car = driver.getCar();
        init();

    }

    public void init() {

        KeyboardEvent keyEventLeft = new KeyboardEvent();
        keyEventLeft.setKey(KeyboardEvent.KEY_LEFT); //37
        keyEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventLeft);

        KeyboardEvent keyEventRight = new KeyboardEvent();
        keyEventRight.setKey(KeyboardEvent.KEY_RIGHT); //39
        keyEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventRight);

        KeyboardEvent keyEventUp = new KeyboardEvent();
        keyEventUp.setKey(KeyboardEvent.KEY_UP); //38
        keyEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventUp);

        KeyboardEvent keyEventDown = new KeyboardEvent();
        keyEventDown.setKey(KeyboardEvent.KEY_DOWN); //40
        keyEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventDown);

        KeyboardEvent keyEventLeftRelease = new KeyboardEvent();
        keyEventLeftRelease.setKey(KeyboardEvent.KEY_LEFT); //38
        keyEventLeftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventLeftRelease);

        KeyboardEvent keyEventRightRelease = new KeyboardEvent();
        keyEventRightRelease.setKey(KeyboardEvent.KEY_RIGHT); //40
        keyEventRightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventRightRelease);

        KeyboardEvent keyEventUpRelease = new KeyboardEvent();
        keyEventUpRelease.setKey(KeyboardEvent.KEY_UP);
        keyEventUpRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventUpRelease);

        KeyboardEvent keyEventDownRelease = new KeyboardEvent();
        keyEventDownRelease.setKey(KeyboardEvent.KEY_DOWN);
        keyEventDownRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventDownRelease);

        KeyboardEvent keyEventSpace = new KeyboardEvent();
        keyEventSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventSpace);

        KeyboardEvent keyEventSpaceRelease = new KeyboardEvent();
        keyEventSpaceRelease.setKey(KeyboardEvent.KEY_SPACE);
        keyEventSpaceRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventSpaceRelease);

        KeyboardEvent keyEventShift = new KeyboardEvent(); //Shift
        keyEventShift.setKey(0x10);
        keyEventShift.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventShift);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        driver.setReversing(false);

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT: { //Left
                //car.setSteerAngle(-1);
                car.steeringWheel(SteerDirection.LEFT);
                System.out.println("l");
                break;
            }
            case KeyboardEvent.KEY_RIGHT: {//Right
                //car.setSteerAngle(1);
                car.steeringWheel(SteerDirection.RIGHT);
                System.out.println("r");
                break;
            }
            case KeyboardEvent.KEY_UP: { //Up
                car.acceleratePedal();
                System.out.println("u");
                break;
            }
            case KeyboardEvent.KEY_DOWN: { //Down
                car.brake();
                System.out.println("d");
                break;
            }
            case KeyboardEvent.KEY_SPACE: { //Space
                car.handBrake();
                System.out.println("space");
                break;
            }
            case 0x10: { //Shift
                if (car.getSpeed() == 0) {
                    car.setGearShift(-car.getGearShift());
                }
                System.out.println("shift");

                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP: {
                System.out.println("u rel");
                //car.brake();
                car.setAcceleration(0);
                break;
            }
            case KeyboardEvent.KEY_DOWN: {
                System.out.println("d rel");
                car.setAcceleration(0);

                break;
            }
            case KeyboardEvent.KEY_LEFT: { //Left
                System.out.println("l rel");
                car.setSteerAngle(0);
                //driver.steerMiddle();
                break;
            }
            case KeyboardEvent.KEY_RIGHT: {//Right
                System.out.println("r rel");
                car.setSteerAngle(0);
                //driver.steerMiddle();
                break;
            }

            case KeyboardEvent.KEY_SPACE: {//Right
                System.out.println("space rel");
                car.setAcceleration(0);
                break;
            }


        }

    }
}
