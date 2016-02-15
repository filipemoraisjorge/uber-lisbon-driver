package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.SteerDirection;
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

    //Keyboard
    private Keyboard keyboard = new Keyboard(this);


    CarKeybHandler(Car car) {
        this.car = car;
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

          /*
    UPRIGHT(-1, -1),
    UP(0, -1), -90, 270
    UPLEFT(1, -1),
    RIGHT(1, 0), 0
    DOWNLEFT(1, 1),
    DOWN(0, 1), 90
    DOWNRIGHT(-1, 1),
    LEFT(-1, 0); 180 -0
  */

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
                car.acceleratePedal();
                break;
            }
            case 40: { //Down
                car.brakePedal();
                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        /*switch (keyboardEvent.getKey()) {
            case 37: { //Left
                car.setDirection(Direction.LEFT);
                break;
            }
            case 39: {//Right
                car.setDirection(Direction.RIGHT);
                break;
            }
            case 38: { //Up
                car.setDirection(Direction.TOP);
                break;
            }
            case 40: { //Down
                car.setDirection(Direction.DOWN);
                break;
            }
        }*/
    }
}
