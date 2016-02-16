package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.simplegraphics.graphics.Color;

/**
 * Created by Filipe Jorge <Academia de Código_> on 02/02/16.
 */
public enum CarType {
    UBERX(UberX.class, 5, ColorUber.VIVIDGREEN.getColor()),
    UBERBLACK(UberBlack.class, 15, ColorUber.BLACK.getColor()),
    UBERSPACE(UberSpace.class, 10, ColorUber.GREEN.getColor()),
    TAXI(Taxi.class, 10, ColorUber.DARKGREEN.getColor());

    private Class carClass;
    private int maxSpeed;
    private int width;
    private int height;

    private Color color;

    CarType(Class carClass, int speed, Color color) {
        this.carClass = carClass;
        this.maxSpeed = speed;
        this.width = 20;
        this.height = 20;
        this.color = color;
    }

    public static int getMaxSize() {
        int size = 0;
        for (CarType cartype : CarType.values()) {
            if (size < cartype.width) {
                size = cartype.width;
            }
        }
        return size;
    }

    public static int getAllMaxSpeed() {
        int speed = 0;
        for (CarType cartype : CarType.values()) {
            if (speed < cartype.maxSpeed) {
                speed = cartype.maxSpeed;
            }
        }
        return speed;
    }

    public Class getCarClass() {
        return carClass;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
