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
        this.car = this.driver.getCar();
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
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case 37: { //Left
                //car.setSteerAngle(-1);
                car.steeringWheel(SteerDirection.LEFT);
                break;
            }
            case 39: {//Right
                //car.setSteerAngle(1);
                car.steeringWheel(SteerDirection.RIGHT);
                break;
            }
            case 38: { //Up
                car.acceleratePedal();
                break;
            }
            case 40: { //Down
                driver.brakePedal();
                break;
            }
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
