package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic.PictureF;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by filipejorge on 15/02/16.
 */
public enum UberCarPicture {
/*    UBERX("resources/uber-car-x.png"),
    UBERSPACE("resources/uber-car-space.png"),
    UBERBLACK("resources/uber-car-black.png"),
    TAXI("resources/uber-car-taxi.png");*/


    UBERX("resources/topview/map-vehicle-icon-uberX.png",1,3,51,22),
    UBERSPACE("resources/topview/map-vehicle-icon-suv.png",0,2,47,22),
    UBERBLACK("resources/topview/map-vehicle-icon-black.png",1,2,50,20),
    TAXI("resources/topview/map-vehicle-icon-taxi.png",0,2,46,20);

    private String file;
    private Picture picture;
    private PictureF pictureF;
    private int[][] hitbox = new int[2][2]; //TODO: make hitbox automatic using alpha

    UberCarPicture(String file, int hitX,int hitY, int hitW, int hitH) {
        this.file = file;
        this.hitbox[0][0] = hitX;
        this.hitbox[0][1] = hitY;
        this.hitbox[1][0] = hitW;
        this.hitbox[1][1] = hitH;
    }

    public Picture getPicture(float x, float y) {
        this.picture = new Picture(x, y, file);

        return picture;
    }

    public PictureF getPictureF(float x, float y) {
        this.pictureF = new PictureF(x, y, file);
        return pictureF;
    }

    public int[][] getHitbox() {
        return hitbox;
    }
}
