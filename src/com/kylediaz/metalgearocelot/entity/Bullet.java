package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.util.Vector;
import com.kylediaz.metalgearocelot.util.geom.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bullet extends PhysicalEntity {

    private static BufferedImage bulletImage;
    static {
        try {
            bulletImage = ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\bullet"));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public Bullet(Point origin, Vector velocity) {
        super(origin.x, origin.y, bulletImage.getWidth(), bulletImage.getHeight());
        setVelocity(velocity);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(bulletImage, (int) getBounds().getX(), (int) getBounds().getY(), null);
    }

}
