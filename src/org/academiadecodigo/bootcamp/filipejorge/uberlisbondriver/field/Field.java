package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field;


import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.*;
import java.util.logging.FileHandler;

public final class Field {

    public static int x;
    public static int y;
    public static int width;
    public static int height;
    private static int MARGIN_LEFT = 10;
    private static int MARGIN_TOP = 10;
    private static int BORDER = 10;
    public static int offsetX = MARGIN_LEFT + BORDER;
    private static int SKY = 108;
    public static int offsetY = MARGIN_TOP + BORDER + SKY;

    //This class is not supposed to be instantiated
    private Field() {
    }

    /**
     * Initializes the Screen
     */
    public static void init() {


        //illustration at the top
        Picture citySkyLine = new Picture(offsetX, offsetY, "resources/uber-skyline.png");
        //image at the field background
        offsetY = offsetY + citySkyLine.getHeight() + BORDER;
        Picture cityBackground1 = new Picture(offsetX, offsetY, "resources/uber-city_background1.png");
        //Picture cityBackground2 = new Picture(MARGIN_LEFT + 1, MARGIN_TOP + 1 + cityBackground1.getHeight() - 1, "resources/uber-city_background2.png");

        //Set field x,y, size, where the playing takes place
        Field.x = cityBackground1.getX();
        Field.y = cityBackground1.getY();
        Field.width = cityBackground1.getWidth();
        Field.height = cityBackground1.getHeight();

        //create a background.
        Rectangle background = new Rectangle(MARGIN_LEFT, MARGIN_TOP, offsetX + width, offsetY + height);
        System.out.println(offsetY + height);
        background.setColor(ColorUber.BLUE.getColor());
        //and a field background
        Rectangle fieldBack = new Rectangle(offsetX, offsetY, width, height);
        fieldBack.setColor(new Color(205, 205, 205));
        //cityBackground2.draw();

        //draw it all
        background.fill();
        fieldBack.fill();
        cityBackground1.draw();
        citySkyLine.draw();



    }
    //TODO:grow to window size method

}
