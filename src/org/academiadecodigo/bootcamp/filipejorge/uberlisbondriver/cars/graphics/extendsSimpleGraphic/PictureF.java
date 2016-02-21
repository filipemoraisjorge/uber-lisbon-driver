package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver.cars.graphics.extendsSimpleGraphic;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Created by filipejorge on 21/02/16.
 */
public class PictureF extends Picture {

    public BufferedImage image;
    private JLabel label = new JLabel();
    private String source;

    //private Raster alpha;

    public PictureF(double v, double v1, String s) {
        super(v, v1, s);
    }

    public PictureF(double v, double v1) {
        super(v, v1);
    }

    public PictureF() {
        super();
    }

    public PictureF(int[][] ints) {
        super(ints);
    }

    public void load(String imageFile) {
        try {
            this.source = imageFile;
            if (imageFile.startsWith("http://")) {
                this.image = ImageIO.read((new URL(imageFile)).openStream());
            } else {
                URL var2 = this.getClass().getResource(imageFile.startsWith("/") ? imageFile : "/" + imageFile);
                if (var2 != null) {
                    this.image = ImageIO.read(var2.openStream());
                } else {
                    this.image = ImageIO.read(new File(imageFile));
                }
            }

            //this.label.setIcon(new ImageIcon(this.image));
            //this.label.setText("");
        } catch (Exception ec) {
            this.image = null;
            //this.label.setIcon((Icon) null);
            ec.printStackTrace();
        }

        Canvas.getInstance().repaint();
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


}
