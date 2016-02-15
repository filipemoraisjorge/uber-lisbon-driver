package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;


import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.ColorUber;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public final class Field {

    private static final int MARGIN_LEFT = 10;
    private static final int MARGIN_TOP = 10;

    public static int width;
    public static int height;

    //This class is not supposed to be instantiated
    private Field() {
    }

    /**
     * Initializes the Screen
     */
    public static void init() {

        // Set field size, where the playing takes place
        Field.width = 1379;
        Field.height = 924;

        Vector.validBoundaries(MARGIN_LEFT + 1, MARGIN_TOP + 1, Field.width - 1 - MARGIN_LEFT, Field.height - 1 - MARGIN_TOP);

        //Draw a border.
        Rectangle fieldBorder = new Rectangle(MARGIN_LEFT, MARGIN_TOP, Field.width - 1 - MARGIN_LEFT, Field.height - 1 - MARGIN_TOP);
        Rectangle fieldBack = new Rectangle(MARGIN_LEFT, MARGIN_TOP, Field.width - 1 - MARGIN_LEFT, Field.height - 1 - MARGIN_TOP);
        fieldBorder.setColor(new Color(200, 200, 200));
        fieldBorder.fill();
        fieldBack.setColor(ColorUber.BLUE.getColor());
        fieldBack.draw();

        Picture cityBackground1 = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + 1, "uber-city_background1.png");
        Picture cityBackground2 = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + 1 + cityBackground1.getHeight() - 1, "uber-city_background2.png");

        cityBackground1.draw();
        cityBackground2.draw();

        Picture citySkyLine = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + height + 10, "uber-skyline.png");
        citySkyLine.draw();

    }

    /**
     * Displays a group of cars in the screen
     *
     * @param cars an array of cars
     */
/*
    public static void draw(Car[] cars) {
        for (Car c : cars) {

            //draw
            c.represetation.draw();
        }
    }*/


}
