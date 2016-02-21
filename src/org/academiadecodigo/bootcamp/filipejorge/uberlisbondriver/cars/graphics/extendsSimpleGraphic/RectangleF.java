package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Created by filipejorge on 21/02/16.
 */
public class RectangleF {

    Collection<Line> lines = new ArrayList<>(4);

    public RectangleF(float x, float y, float width, float height) {
        //points ABCD clockwise
        //A (x,y)
        //B (x+width, y)
        //C (x+width, y+height)
        //D (x, y+height)
        float xC = (width/2);
        float yC = (height/2);
        //AB
        Line AB = new Line(x, y, x+width, y);
        //lines.add(AB);
        //BC
        Line BC = new Line(x + width, y, x + width, y + height);
        lines.add(BC);
        //CD
        Line CD = new Line(x + width, y + height, x, y + height);
        //lines.add(CD);
        //DA
        Line DA = new Line(x, y + height, x, y);
        //lines.add(DA);


    }

    public void setColor(Color color) {
        Iterator<Line> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            lineIterator.next().setColor(color);
        }
    }

    public void draw() {
        Iterator<Line> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            Line ln = lineIterator.next();
            ln.draw();
        }
    }

    public void delete() {
        Iterator<Line> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            lineIterator.next().delete();
        }
    }

    public void translate(double x, double y) {
        Iterator<Line> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            lineIterator.next().translate(x, y);
        }
    }

    public void grow(double x, double y) {
        Iterator<Line> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            lineIterator.next().grow(x, y);
        }
    }

    public void rotate(float angle) {
        float angleRad = (float) Math.toRadians(angle);
        float xAng = (float) Math.cos(angleRad);
        float yAng = (float) Math.sin(angleRad);
        //this.translate(xAng, yAng);
        this.grow(xAng,yAng);
    }
}
