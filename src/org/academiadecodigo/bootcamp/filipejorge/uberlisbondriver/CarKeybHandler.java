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
        KeyboardEvent keyEventRigth = new KeyboardEvent();

        KeyboardEvent keyEventUp = new KeyboardEvent();
        KeyboardEvent keyEventDown = new KeyboardEvent();

        keyEventLeft.setKey(KeyboardEvent.KEY_LEFT); //37
        keyEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventLeft);

        keyEventRigth.setKey(KeyboardEvent.KEY_RIGHT); //39
        keyEventRigth.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventRigth);

        keyEventUp.setKey(KeyboardEvent.KEY_UP); //38
        keyEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventUp);

        keyEventDown.setKey(KeyboardEvent.KEY_DOWN); //40
        keyEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyEventDown);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case 37: { //Left
                car.steeringWheel(SteerDirection.LEFT);
                break;
            }
            case 39: {//Right
                car.steeringWheel(SteerDirection.RIGHT);
                break;
            }
            case 38: { //Up
                driver.accelerate();
                break;
            }
            case 40: { //Down
                driver.stop();
                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
