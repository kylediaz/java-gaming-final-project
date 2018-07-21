package com.kylediaz.nameofgame;

import com.kylediaz.nameofgame.entity.Entity;
import com.kylediaz.nameofgame.util.Camera;
import com.kylediaz.nameofgame.util.Time;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author kyled
 */
public class Bullet extends Entity {

    BufferedImage it;
    
    double radians;
    
    public Bullet(Game game, File directory) throws IOException {
        super(game, directory);
        it = ImageIO.read(new File(directory.getAbsoluteFile() + "\\1.png"));
    }
    public Bullet(Game game, File directory, Point2D.Double origin, double radians) throws IOException {
        super(game, directory);
        it = ImageIO.read(new File(directory.getAbsoluteFile() + "\\1.png"));
        bounds = new Rectangle2D.Double(0,0,0,0);
        bounds.x = origin.x; bounds.y = origin.y;
        bounds.width = 3; bounds.height = 3;
        this.radians = radians;
    }

    @Override
    public void tick() {
        bounds.x = bounds.x + (300 * Time.deltaTimeMilli() / 1000 * Math.cos(radians));
        bounds.y = bounds.y + (300 * Time.deltaTimeMilli() / 1000 * Math.sin(radians));
        if (game.map.isOccupiedSpot(bounds)) game.remove(this);
        for (Entity entity : (ArrayList<Entity>) game.entityDirectory.clone()) {
            if (entity != this && bounds.intersects(entity.bounds)) {
                game.timeGiven += 1.75;
                game.remove(this);
                game.remove(entity);
            }
        }
    }

    @Override
    public Point2D.Double getFocalPoint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics2D g2d, Camera camera) {
        g2d.translate(bounds.x, bounds.y);
        g2d.drawImage(it, 0, 0, game);
        g2d.translate(-bounds.x, -bounds.y);
    }
    
}
