package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.Car;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.ColorUber;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.Representation;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Position;
import org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.field.Vector;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

/**
 * Created by filipejorge on 22/02/16.
 */
public class GameMarker extends Representation {

    private Ellipse marker;
    private MarkerType type;
    private int size = 15;

    public GameMarker(MarkerType type, int size) {
        super(size, size);
        this.size = size;
        Position pos = super.getVector().getPos();
        float x = pos.getX();
        float y = pos.getY();
        marker = new Ellipse(x, y, this.size, this.size);
        switch (type) {
            case START:
                marker.setColor(ColorUber.VIVIDGREEN.getColor());
                break;
            case END:
                marker.setColor(ColorUber.RED.getColor());
        }
        marker.fill();

    }

    public void delete() {
        marker.delete();
        //marker = null;
    }

    public int distance(GameMarker another) {
        double x = this.getVector().getPos().getX() - another.getVector().getPos().getX();
        double y = this.getVector().getPos().getY() - another.getVector().getPos().getY();
        return (int) Math.hypot(x, y);
    }

    public boolean isReached(Car car) {
        return car.getRepresentation().collide(this) && (car.getSpeed() == 0);
    }

    public enum MarkerType {
        START,
        END,
    }

}
