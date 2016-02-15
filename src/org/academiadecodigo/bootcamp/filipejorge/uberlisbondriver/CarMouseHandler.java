package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

/**
 * Created by filipejorge on 10/02/16.
 */
public class CarMouseHandler implements MouseHandler {

    //the Car to be controlled;
    private Car car;
    private Mouse mouse = new Mouse(this);

    CarMouseHandler(Car car) {
        this.car = car;
        init();

    }

    private void init() {
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
 /*       int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        Position mousePos = new Position(x, y);
        Position carPos = this.car.getPos();

        Position dirAsPos = carPos.directionTo(mousePos);
        Direction dir = Direction.findDirection(dirAsPos.getX(),dirAsPos.getY());

        car.setDirection(dir);*/

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
/*
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();

        Direction dir = new Direction(x,y);

       Position mousePos = new Position(x, y);
        Position carPos = this.car.getPos();

        Position dirAsPos = carPos.directionTo(mousePos);
        Direction dir = Direction.findDirection(dirAsPos.getX(),dirAsPos.getY());

        car.setDirection(dir);
        */


    }
}
