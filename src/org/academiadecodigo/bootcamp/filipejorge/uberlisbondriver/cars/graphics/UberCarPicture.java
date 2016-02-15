package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by filipejorge on 15/02/16.
 */
public enum UberCarPicture {
    UBERX("uber-car-x.png"),
    UBERSPACE("uber-car-space.png"),
    UBERBLACK("uber-car-black.png"),
    TAXI("uber-car-taxi.png");

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
