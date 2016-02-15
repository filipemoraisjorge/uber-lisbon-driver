package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars;

import org.academiadecodigo.simplegraphics.graphics.Color;

/**
 * Created by filipejorge on 15/02/16.
 */
public enum ColorUber {
    BLUE(new Color(88, 121, 218)),
    GREEN(new Color(137, 218, 193)),
    DARKGREEN(new Color(38, 126, 99)),
    VIVIDGREEN(new Color(77, 193, 156)),
    BLACK(Color.BLACK);

    private Color color;

    ColorUber(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
