package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;


import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.logging.FileHandler;

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

        //Load background
        Picture cityBackground1 = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + 1, "resources/uber-city_background1.png");
        //Picture cityBackground2 = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + 1 + cityBackground1.getHeight() - 1, "resources/uber-city_background2.png");

        //Set field size, where the playing takes place
        Field.width = cityBackground1.getWidth() + 1;
        Field.height = cityBackground1.getHeight() + 1;

        //Set the valid boundaries where vectors can be created/moved
        //second thoughts, Shouldn't Field define this, whats the propose of Class Field
        Vector.validBoundaries(MARGIN_LEFT + 1, MARGIN_TOP + 1, Field.width - 1 - MARGIN_LEFT, Field.height - 1 - MARGIN_TOP);

        //Draw a border.
        Rectangle fieldBorder = new Rectangle(MARGIN_LEFT, MARGIN_TOP, width, height);
        Rectangle fieldBack = new Rectangle(MARGIN_LEFT, MARGIN_TOP, width, height);
        fieldBorder.setColor(new Color(205, 205, 205));
        fieldBorder.fill();
        fieldBack.setColor(ColorUber.BLUE.getColor());

        //draw it all
        fieldBack.draw();
        cityBackground1.draw();
        //cityBackground2.draw();

        Picture citySkyLine = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + height + 10, "resources/uber-skyline.png");
        citySkyLine.draw();
    }

}
