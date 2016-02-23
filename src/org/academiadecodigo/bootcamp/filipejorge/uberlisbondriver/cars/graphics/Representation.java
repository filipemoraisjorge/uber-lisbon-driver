package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.PlayerCar;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic.PictureF;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Position;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Vector;
import org.academiadecodigo.simplegraphics.graphics.*;

/**
 * Created by filipejorge on 12/02/16.
 */
public class Representation {
//TODO: change to interface, representable applied to all the gameobjects

    boolean inRoute;
    private Vector vector;
    private Vector lastVector;
    private int width;
    private int height;
    private Color color;
   // private Picture picture;
    private int[][] hitBox;
    private PictureF pictureF;
    private Ellipse circleDir;

    public Representation(int w, int h) {

        this.width = w;
        this.height = h;
        this.vector = new Vector(width, height);

    }

    public Representation(Car car) {

        //temp picture to get width and height. It's a sign that something is no well structured...
        this.pictureF = UberCarPicture.valueOf(car.getCarType().toString()).getPictureF(0, 0,0);


        this.width = this.pictureF.getWidth();
        this.height = this.pictureF.getHeight();
        this.vector = new Vector(width, height);
        this.lastVector = new Vector(vector);

        float x = this.vector.getPos().getX();
        float y = this.vector.getPos().getY();
        //picture in right position
        double angle = Math.toRadians(this.vector.getDir().getAngle());
        this.pictureF = UberCarPicture.valueOf(car.getCarType().toString()).getPictureF(x, y,angle) ;
        this.hitBox = UberCarPicture.valueOf(car.getCarType().toString()).getHitbox();

        this.color = car.getCarType().getColor();

        if (car instanceof PlayerCar) {

            float xCenter = x + (width / 2);
            float yCenter = y + (height / 2);
            this.circleDir = new Ellipse(xCenter, yCenter, 3, 3); //start position marker
            this.circleDir.setColor(this.color);

        }

        this.pictureF.draw();

    }

    public PictureF getPicture() {
        return pictureF;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector getVector() {
        return vector;
    }

    public boolean isInRoute() {
        return inRoute;
    }

    public void setInRoute(boolean inRoute) {
        this.inRoute = inRoute;
    }

    public void setCrashed() {
        //this.color = new Color(this.color.getRed() / 2, this.color.getGreen() / 2, this.color.getBlue() / 2);
    }

    public boolean isOnEdge() {
        return vector.isOnEdge();
    }

    public void move(float speed, float angle, int shift) {
        //save lastVector
        this.lastVector = new Vector(this.vector);
        //move vector
        this.vector.move(speed, angle, shift);
        //draw representation
        this.draw();

    }


    public void draw() {

        //draw shape
        Position lastPos = this.lastVector.getPos();
        Position pos = this.vector.getPos();

        float xRelative = pos.getX() - lastPos.getX();
        float yRelative = pos.getY() - lastPos.getY();

        if (inRoute) { //trace path
            //TODO: should be in a container so it can be erased when the Route is finished
            adjustDirLine(pos.getX(), pos.getY());
            this.circleDir.fill();
        }

        this.pictureF.translate(xRelative, yRelative);
        double angle = Math.toRadians(this.vector.getDir().getAngle());
        this.pictureF.setAngle(angle);



    }

    private void adjustDirLine(float x, float y) {
        float xCenter = this.vector.getPos().getX() + (width / 2);
        float yCenter = this.vector.getPos().getY() + (height / 2);
        this.circleDir = new Ellipse(xCenter, yCenter, 3, 3);
        this.circleDir.setColor(this.color);

        /* radical point calculations
        // x = r × cos( θ )
        // y = r × sin( θ )
        float xDir = (width / 2) * (float) (Math.cos(this.vector.getDir().getAngleRad()));
        float yDir = (height / 2) * (float) (Math.sin(this.vector.getDir().getAngleRad()));
        */

    }

    public boolean collide(Representation another) {

        Position thisPos = vector.getPos();
        Position anotherPos = another.vector.getPos();

        float thisX = thisPos.getX();
        float thisY = thisPos.getY();

        float anotherX = anotherPos.getX();
        float anotherY = anotherPos.getY();

        //usign pictures hitbox;
        if ((this.getPicture() != null) && (another.getPicture() != null)) {
            //TODO: with rotation implemented this method doent work very well.
            int thisHitX = hitBox[0][0];
            int thisHitY = hitBox[0][1];
            int thisHitWidth = hitBox[1][0];
            int thisHitHeight = hitBox[1][1];

            int anotherHitX = another.hitBox[0][0];
            int anotherHitY = another.hitBox[0][1];
            int anotherHitW = another.hitBox[1][0];
            int anotherHitH = another.hitBox[1][1];

            return ((Math.abs(thisX + thisHitX - anotherX + anotherHitX) < thisHitWidth - thisHitX)
                    && (Math.abs(thisY + thisHitY - anotherY + anotherHitY) < thisHitHeight - thisHitY));
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


