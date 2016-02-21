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


    CarKeybHandler(Car car) {
        //this.driver = driver;
        this.car = car;
        init();

    }

    public void init() {
        KeyboardEvent keyEventLeft = new KeyboardEvent();
        KeyboardEvent keyEventRight = new KeyboardEvent();

        KeyboardEvent keyEventUp = new KeyboardEvent();
        KeyboardEvent keyEventDown = new KeyboardEvent();

        KeyboardEvent keyEventLeftRelease = new KeyboardEvent();
        KeyboardEvent keyEventRightRelease = new KeyboardEvent();

        keyEventLeft.setKey(KeyboardEvent.KEY_LEFT); //37
        keyEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventLeft);

        keyEventRight.setKey(KeyboardEvent.KEY_RIGHT); //39
        keyEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventRight);

        keyEventUp.setKey(KeyboardEvent.KEY_UP); //38
        keyEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventUp);

        keyEventDown.setKey(KeyboardEvent.KEY_DOWN); //40
        keyEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventDown);

        keyEventLeftRelease.setKey(KeyboardEvent.KEY_LEFT); //38
        keyEventLeftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventLeftRelease);

        keyEventRightRelease.setKey(KeyboardEvent.KEY_RIGHT); //40
        keyEventRightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(keyEventRightRelease);

        KeyboardEvent keyEventSpace = new KeyboardEvent();
        keyEventSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventSpace);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT: { //Left
                //car.setSteerAngle(-1);
                car.steeringWheel(SteerDirection.LEFT);
                break;
            }
            case KeyboardEvent.KEY_RIGHT: {//Right
                //car.setSteerAngle(1);
                car.steeringWheel(SteerDirection.RIGHT);
                break;
            }
            case KeyboardEvent.KEY_UP: { //Up
                car.acceleratePedal();
                break;
            }
            case KeyboardEvent.KEY_DOWN: { //Down
                car.brake();
                break;
            }

            case KeyboardEvent.KEY_SPACE:


        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case 37: { //Left
                car.setSteerAngle(0);
                //driver.steerMiddle();
                break;
            }
            case 39: {//Right
                car.setSteerAngle(0);
                //driver.steerMiddle();
                break;
            }
        }

    }
}
