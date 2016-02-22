package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.graphics.Shape;

/**
 * Created by filipejorge on 21/02/16.
 */
public class PictureF implements org.academiadecodigo.simplegraphics.graphics.Shape, Movable {


    private BufferedImage image;
    private JLabel label = new JLabel();
    private String source;
    private double x;
    private double y;
    private double xGrow;
    private double yGrow;
    private double angle;

    public PictureF() {
    }

    public PictureF(double var1, double var3) {
        this.image = new BufferedImage((int) Math.round(var1), (int) Math.round(var3), 1);
        this.label.setIcon(new ImageIcon(this.image));
        this.label.setText("");
    }

    public PictureF(double var1, double var3, double angle, String var5) {
        this.x = var1;
        this.y = var3;
        this.angle = angle;
        this.load(var5);
    }

    public PictureF(int[][] var1) {
        this.image = new BufferedImage(var1[0].length, var1.length, 1);

        for (int var2 = 0; var2 < this.image.getWidth(); ++var2) {
            for (int var3 = 0; var3 < this.image.getHeight(); ++var3) {
                int var4 = var1[var3][var2];
                if (var4 < 0) {
                    var4 = 0;
                }

                if (var4 > 255) {
                    var4 = 255;
                }

                int var5 = var4 * 65793;
                this.image.setRGB(var2, var3, var5);
            }
        }

        this.label.setIcon(new ImageIcon(this.image));
        this.label.setText("");
    }

    public void load(String var1) {
        try {
            this.source = var1;
            if (var1.startsWith("http://")) {
                this.image = ImageIO.read((new URL(var1)).openStream());
            } else {
                URL var2 = this.getClass().getResource(var1.startsWith("/") ? var1 : "/" + var1);
                if (var2 != null) {
                    this.image = ImageIO.read(var2.openStream());
                } else {
                    this.image = ImageIO.read(new File(var1));
                }
            }

            this.label.setIcon(new ImageIcon(this.image));
            this.label.setText("");
        } catch (Exception var3) {
            this.image = null;
            this.label.setIcon((Icon) null);
            var3.printStackTrace();
        }

        Canvas.getInstance().repaint();
    }

    public int getX() {
        return (int) Math.round(this.x - this.xGrow);
    }

    public int getY() {
        return (int) Math.round(this.y - this.yGrow);
    }

    public int getMaxX() {
        return this.getX() + this.getWidth();
    }

    public int getMaxY() {
        return this.getY() + this.getHeight();
    }

    public int getWidth() {
        return (int) Math.round((double) (this.image == null ? 0 : this.image.getWidth()) + 2.0D * this.xGrow);
    }

    public int getHeight() {
        return (int) Math.round((double) (this.image == null ? 0 : this.image.getHeight()) + 2.0D * this.yGrow);
    }

    public int pixels() {
        return this.image == null ? 0 : this.image.getWidth() * this.image.getHeight();
    }

    public int[][] getGrayLevels() {
        if (this.image == null) {
            return new int[0][0];
        } else {
            int[][] var1 = new int[this.getHeight()][this.getWidth()];

            for (int var2 = 0; var2 < var1.length; ++var2) {
                for (int var3 = 0; var3 < var1[var2].length; ++var3) {
                    int var4 = this.image.getRGB(var3, var2);
                    var1[var2][var3] = (int) (0.2989D * (double) (var4 >> 16 & 255) + 0.5866D * (double) (var4 >> 8 & 255) + 0.1144D * (double) (var4 & 255));
                }
            }

            return var1;
        }
    }

    public String toString() {
        return "Picture[x=" + this.getX() + ",y=" + this.getY() + ",width=" + this.getWidth() + ",height=" + this.getHeight() + ",source=" + this.source + "]";
    }

    public Color getColorAt(int var1) {
        if (this.image != null && var1 >= 0 && var1 < this.pixels()) {
            return this.getColorAt(var1 % this.image.getWidth(), var1 / this.image.getWidth());
        } else {
            throw new IndexOutOfBoundsException("" + var1);
        }
    }

    public void setColorAt(int var1, Color var2) {
        if (this.image != null && var1 >= 0 && var1 < this.pixels()) {
            this.setColorAt(var1 % this.image.getWidth(), var1 / this.image.getWidth(), var2);
        } else {
            throw new IndexOutOfBoundsException("" + var1);
        }
    }

    public Color getColorAt(int var1, int var2) {
        if (this.image != null && var1 >= 0 && var1 < this.image.getWidth() && var2 >= 0 && var2 < this.image.getHeight()) {
            int var3 = this.image.getRGB(var1, var2) & 16777215;
            return new Color(var3 / 65536, var3 / 256 % 256, var3 % 256);
        } else {
            throw new IndexOutOfBoundsException("(" + var1 + "," + var2 + ")");
        }
    }

    public void setColorAt(int var1, int var2, Color var3) {
        if (this.image != null && var1 >= 0 && var1 < this.image.getWidth() && var2 >= 0 && var2 < this.image.getHeight()) {
            this.image.setRGB(var1, var2, var3.getRed() * 65536 + var3.getGreen() * 256 + var3.getBlue());
            Canvas.getInstance().repaint();
        } else {
            throw new IndexOutOfBoundsException("(" + var1 + "," + var2 + ")");
        }
    }

    public void translate(double var1, double var3) {
        this.x += var1;
        this.y += var3;
        Canvas.getInstance().repaint();
    }

    public void grow(double var1, double var3) {
        this.xGrow += var1;
        this.yGrow += var3;
        Canvas.getInstance().repaint();
    }

    public void rotate(double angle) {
       this.angle += angle;
        Canvas.getInstance().repaint();
    }

    public void setAngle(double angle) {
        this.angle = angle;
        Canvas.getInstance().repaint();
    }
    public void draw() {
        Canvas.getInstance().show(this);
    }

    public void delete() {
        Canvas.getInstance().hide(this);
    }

    public void paintShape(Graphics2D g) {
        if (this.image != null) {
            Dimension dim = this.label.getPreferredSize();
            if (dim.width > 0 && dim.height > 0) {
                this.label.setBounds(0, 0, dim.width, dim.height);
                g.translate(this.getX(), this.getY());

                // 3. translate it to the center of the component
                g.translate(getWidth() / 2, getHeight() / 2);
                // 2. do the actual rotation
                g.rotate(this.angle);
                // 1. translate the object so that you rotate it around the
                g.translate(-image.getWidth() / 2, -image.getHeight() / 2);

                g.scale(((double) this.image.getWidth() + 2.0D * this.xGrow) / (double) dim.width, ((double) this.image.getHeight() + 2.0D * this.yGrow) / (double) dim.height);
                this.label.paint(g);
                System.out.println("paintShape");
            }
        }

    }


    public int getAlphaAt(int var1, int var2) {
/*        if (this.image.getType() != BufferedImage.TYPE_INT_ARGB) {
            throw new RuntimeException("Wrong image type.");
        } else {*/
        if (this.image != null && var1 >= 0 && var1 < this.image.getWidth() && var2 >= 0 && var2 < this.image.getHeight()) {
            int argbInt = this.image.getRGB(var1, var2);
            return (argbInt >> 24) & 0xff;
        } else {
            throw new IndexOutOfBoundsException("(" + var1 + "," + var2 + ")");
        }
    }

/*    public void rotate(float angle) {

        double angleRad = Math.toRadians(angle);

        // create the transform, note that the transformations happen
        // in reversed order (so check them backwards)
        AffineTransform at = new AffineTransform();

        // 4. translate it to the center of the component
        at.translate(getWidth() / 2, getHeight() / 2);

        // 3. do the actual rotation
        at.rotate(angleRad);

        // 1. translate the object so that you rotate it around the
        //    center (easier :))
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

        Graphics2D g = (Graphics2D) image.createGraphics();
        g.rotate(angleRad);
        g.drawImage(image, at, null);

       //  g.dispose();

        this.label.paint(g);

        Canvas.getInstance().repaint();


    }*/

}
