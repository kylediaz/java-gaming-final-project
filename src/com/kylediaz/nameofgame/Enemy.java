package com.kylediaz.nameofgame;

import com.kylediaz.nameofgame.entity.Entity;
import com.kylediaz.nameofgame.util.Camera;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kyled
 */
public class Enemy extends Entity {

    public Enemy(Game game, File directory) {
        super(game, directory);
    }
    BufferedImage TPOSE;
    public Enemy(Game game, File directory, Point2D.Double spawn) {
        super(game, directory);
        this.bounds = new Rectangle2D.Double(spawn.x, spawn.y, 14, 23);
        try {
            TPOSE = ImageIO.read(new File(directory.getAbsoluteFile() + "\\TPOSE.png"));
        } catch (IOException ex) {
            Game.LOGGER.log(Level.SEVERE, "Enemy image was not loaded");
        }
    }

    @Override
    public void tick() {
        bounds.x = bounds.x + (game.player.bounds.x - bounds.x) / 500000;
        bounds.y = bounds.y + (game.player.bounds.y - bounds.y) / 500000;
        if (bounds.intersects(game.player.bounds)) {
            Game.LOGGER.log(Level.INFO, "Touched by enemy");
            game.endGame();
        }
    }

    @Override
    public void render(Graphics2D g2d, Camera camera) {
        g2d.translate(bounds.x, bounds.y);
        g2d.drawImage(TPOSE, 0, 0, game);
        g2d.translate(-bounds.x, -bounds.y);
    }
    
}
