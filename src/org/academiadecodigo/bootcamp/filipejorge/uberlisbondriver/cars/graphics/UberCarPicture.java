package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by filipejorge on 15/02/16.
 */
public enum UberCarPicture {
    UBERX("resources/uber-car-x.png"),
    UBERSPACE("resources/uber-car-space.png"),
    UBERBLACK("resources/uber-car-black.png"),
    TAXI("resources/uber-car-taxi.png");

    private String file;
    private Picture picture;

    UberCarPicture(String file) {
        this.file = file;
    }

    public Picture getPicture(float x, float y) {
        this.picture = new Picture(x, y, file);
        return picture;
    }
}
