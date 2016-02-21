package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic.PictureF;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Position;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Vector;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by filipejorge on 12/02/16.
 */
public class Representation {
//TODO: change to interface

    private Vector vector;
    private Vector lastVector;

    private int width;
    private int height;
    private Color color;

    private Picture picture;
    private  int[][] hitBox;
    //private Rectangle rectangle;
    private Ellipse circleDir;
    //private Line dirLine;
    //private Text infoText;
    //private RectangleF rectangleF;


    public Representation(Car car) {

        //temp picture to get width and height. It's a sign that something is no well structured...
        this.picture = UberCarPicture.valueOf(car.getCarType().toString()).getPicture(0, 0);


        this.width = this.picture.getWidth();
        this.height = this.picture.getHeight();
        this.vector = new Vector(width, height);
        this.lastVector = new Vector(vector);

        float x = this.vector.getPos().getX();
        float y = this.vector.getPos().getY();
        //picture in right position
        this.picture = UberCarPicture.valueOf(car.getCarType().toString()).getPicture(x, y);
        this.hitBox = UberCarPicture.valueOf(car.getCarType().toString()).getHitbox();

        //my rectangle lines, it rotates!
        //this.rectangleF = new RectangleF(x, y, width, height);

        this.color = car.getCarType().getColor();
        //this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        //this.rectangleF.setColor(this.color);


        //this.rectangle = new Rectangle(this.vector.getPos().getX(), this.vector.getPos().getY(), width, height);
        //this.rectangle.setColor(this.color);
        //this.rectangle.fill();

        float xCenter = x + (width / 2);
        float yCenter = y + (height / 2);
        this.circleDir = new Ellipse(xCenter, yCenter, 15, 15); //start position marker
        this.circleDir.setColor(this.color);

        //this.dirLine = new Line(xCenter, yCenter, this.rectangle.getX(), this.rectangle.getY());
        //this.dirLine.draw();
        //this.infoText = new Text(this.vector.getPos().getX(), this.vector.getPos().getY(), "");
        //this.draw();

        this.picture.draw();
        this.circleDir.fill();
        //this.rectangleF.draw();

        //adjust Direction
        if (Math.signum(this.vector.getDir().getxDir()) == -1) {
            this.picture.grow(-width, 0);
        }

    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Vector getVector() {
        return vector;
    }

    public void setInfoText(String infoText) {
        //this.infoText = new Text(this.vector.getPos().getX(), this.vector.getPos().getY(), infoText);
    }

    public void setCrashed() {
        this.picture.setColorAt(1, 1, this.color);
        this.color = new Color(this.color.getRed() / 2, this.color.getGreen() / 2, this.color.getBlue() / 2);
        //this.rectangle.setColor(this.color);
        //this.rectangle.draw();
    }

    public boolean isOnEdge() {
        return vector.isOnEdge();
    }

    public void move(int speed, float angle, int shift) {
        //save lastVector
        this.lastVector = new Vector(this.vector);
        //move vector
        this.vector.move(speed, angle, shift);
        //draw representation
        this.draw();
        //}
    }


    public void draw() {

        //draw shape
        Position lastPos = this.lastVector.getPos();
        Position pos = this.vector.getPos();

        float xRelative = pos.getX() - lastPos.getX();
        float yRelative = pos.getY() - lastPos.getY();


        adjustDirLine(pos.getX(), pos.getY());
        //this.dirLine.translate(xRelative, yRelative);
        //this.dirLine.draw();
        this.circleDir.fill();
        //this.rectangle.setColor(this.color);
        //this.rectangle.translate(xRelative, yRelative);
        this.picture.translate(xRelative, yRelative);
        adjustToPictureDirection();

        //this.rectangleF.translate(xRelative, yRelative);
        //this.rectangleF.rotate(this.vector.getDir().getAngle());
        //this.infoText.setColor(Color.BLACK);
        //this.infoText.translate(xRelative, yRelative);
        //this.infoText.draw();

        /*
        try {
            Thread.sleep(Game.delay);
        } catch (InterruptedException ex) {
            System.out.printf("thread interrupted exception");
        }
        */

    }

    private void adjustToPictureDirection() {
        if (Math.signum(this.vector.getDir().getxDir()) != Math.signum(this.lastVector.getDir().getxDir())) {
            this.picture.grow((Math.signum(this.vector.getDir().getxDir()) * width), 0);
        }
    }

    private void adjustDirLine(float x, float y) {
        float xCenter = this.vector.getPos().getX() + (width / 2);
        float yCenter = this.vector.getPos().getY() + (height / 2);

        /*
        // x = r × cos( θ )
        // y = r × sin( θ )
        float xDir = (width / 2) * (float) (Math.cos(this.vector.getDir().getAngleRad()));
        float yDir = (height / 2) * (float) (Math.sin(this.vector.getDir().getAngleRad()));

        float xDirCenter = xCenter + xDir;
        float yDirCenter = yCenter + yDir;
        */

        //this.dirLine = new Line(xCenter, yCenter, xDirCenter, yDirCenter);
        //this.dirLine = new Line(xCenter, yCenter, xCenter, yCenter);
        //this.dirLine.setColor(this.color);

        this.circleDir = new Ellipse(xCenter, yCenter, 3, 3);
        this.circleDir.setColor(this.color);

    }

    public boolean collide(Representation another) {

        Position thisPos = vector.getPos();
        Position anotherPos = another.vector.getPos();

        float thisX = thisPos.getX();
        float thisY = thisPos.getY();

        float anotherX = anotherPos.getX();
        float anotherY = anotherPos.getY();

        //usign pictures hitbox;
        if ((this.getPicture() != null)||(another.getPicture() != null)) {
            //TODO: seems to work but should do more tests
            int thisHitX = hitBox[0][0];
            int thisHitY = hitBox[0][1];
            int thisHitW = hitBox[1][0];
            int thisHitH = hitBox[1][1];

            int anotherHitX = another.hitBox[0][0];
            int anotherHitY = another.hitBox[0][1];
            int anotherHitW = another.hitBox[1][0];
            int anotherHitH = another.hitBox[1][1];

            return ((Math.abs(thisX + thisHitX - anotherX + anotherHitX) < thisHitW) && (Math.abs(thisY + thisHitY - anotherY + anotherHitY) < thisHitH));

        } else {
            //Rectangles
            //TODO: Different forms
            return ((Math.abs(thisX - anotherX) < width) && (Math.abs(thisY - anotherY) < height));
        }
    }


    @Override
    public String toString() {
        return "x " + vector.getPos().getY() +
                " y " + vector.getPos().getX() +
                " a " + vector.getDir().getAngle() +
                " edge? " + vector.isOnEdge();
    }
}


