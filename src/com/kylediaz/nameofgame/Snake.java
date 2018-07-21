package com.kylediaz.nameofgame;

import com.kylediaz.nameofgame.util.Time;
import com.kylediaz.nameofgame.util.Camera;
import com.kylediaz.nameofgame.entity.PlayableEntity;
import com.kylediaz.nameofgame.input.Mouse;
import com.kylediaz.nameofgame.util.ConvertImageToCodeToImageInator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The normal entities are drawn using images in a directory, so I have to make
 * subclass to re-do the drawing logic so it wont use drawImage()
 *
 * @author kyled
 */
public class Snake extends PlayableEntity {

    BufferedImage upSprite;
    BufferedImage downSprite;
    BufferedImage leftSprite;
    BufferedImage rightSprite;
    BufferedImage upLeftSprite;
    BufferedImage upRightSprite;
    BufferedImage downLeftSprite;
    BufferedImage downRightSprite;

    BufferedImage shootUp;
    BufferedImage shootDown;
    BufferedImage shootLeft;
    BufferedImage shootRight;
    BufferedImage shootUpLeft;
    BufferedImage shootUpRight;
    BufferedImage shootDownLeft;
    BufferedImage shootDownRight;

    File bulletDir;

    public Snake(Game game, File directory, ArrayList<Integer> keyboard, Mouse mouse) {
        super(game, directory, keyboard, mouse);
        try {
//            File tempText = new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\temp\\temp.txt");
//            File tempJava = new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\temp\\Temp.java");
            upSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\up.png"));
//            upSprite = ConvertImageToCodeToImageInator.convert(upSprite, tempText, tempJava);
            downSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\down.png"));
            leftSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\left.png"));
            rightSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\right.png"));
            upLeftSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\upLeft.png"));
            upRightSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\upRight.png"));
            downLeftSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\downLeft.png"));
            downRightSprite = ImageIO.read(new File(directory.getAbsoluteFile() + "\\downRight.png"));

            shootUp = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootUp.png"));
            shootDown = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootDown.png"));
            shootLeft = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootLeft.png"));
            shootRight = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootRight.png"));
            shootUpLeft = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootUpLeft.png"));
            shootUpRight = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootUpRight.png"));
            shootDownLeft = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootDownLeft.png"));
            shootDownRight = ImageIO.read(new File(directory.getAbsoluteFile() + "\\shootDownRight.png"));

            bulletDir = new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\entities\\bullet");

        } catch (Exception e) {
            System.out.println("da");
        }
        frame = downSprite;
        bounds = new Rectangle2D.Double(20, 20, 10, 19);
    }
    public double health = 200;
    Rectangle walkHitbox = new Rectangle(2, 14, 7, 3);

    @Override
    public void tick() {
        Rectangle2D.Double boundsCopy = (Rectangle2D.Double) bounds.clone();
        boolean shot = false;
        boolean up = false, down = false, left = false, right = false;
        for (Integer keyCode : (ArrayList<Integer>) pressedKeys.clone()) {
            if (keyCode == null) {
                continue;
            }
            if (keyCode == KeyEvent.VK_W) {
                boundsCopy.y -= 50 * (Time.deltaTimeMilli() / 1000);
                up = true;
            } else if (keyCode == KeyEvent.VK_S) {
                boundsCopy.y += 50 * (Time.deltaTimeMilli() / 1000);
                down = true;
            } else if (keyCode == KeyEvent.VK_A) {
                boundsCopy.x -= 50 * (Time.deltaTimeMilli() / 1000);
                left = true;
            } else if (keyCode == KeyEvent.VK_D) {
                boundsCopy.x += 50 * (Time.deltaTimeMilli() / 1000);
                right = true;
            } else if (keyCode == KeyEvent.VK_SPACE) {
                shot = true;
            } else if (keyCode == KeyEvent.VK_P) {

            }
        }
        if (!game.map.isOccupiedSpot(new Rectangle2D.Double(walkHitbox.x + boundsCopy.x, walkHitbox.y + boundsCopy.y,
                walkHitbox.width, walkHitbox.height))) {
            bounds = boundsCopy;
        }
        try {
            if (shot) {
                if (up && right) {
                    frame = shootUpRight;
                    tryToShoot(-Math.PI / 4, new Point2D.Double(bounds.x, bounds.y + 10));
                } else if (up && left) {
                    frame = shootUpLeft;
                    tryToShoot(-3 * Math.PI / 4, new Point2D.Double(bounds.x, bounds.y));
                } else if (down && right) {
                    frame = shootDownRight;
                    tryToShoot(Math.PI / 4, new Point2D.Double(bounds.x + 9, bounds.y + 10));
                } else if (down && left) {
                    frame = shootDownLeft;
                    tryToShoot(3 * Math.PI / 4, new Point2D.Double(bounds.x, bounds.y + 7));
                } else if (up) {
                    frame = shootUp;
                    tryToShoot(-Math.PI / 2, new Point2D.Double(bounds.x + 4, bounds.y));
                } else if (down) {
                    frame = shootDown;
                    tryToShoot(Math.PI / 2, new Point2D.Double(bounds.x + 3, this.getFocalPoint().y + 2));
                } else if (left) {
                    frame = shootLeft;
                    tryToShoot(Math.PI, new Point2D.Double(bounds.x, bounds.y + 2));
                } else if (right) {
                    frame = shootRight;
                    tryToShoot(0, new Point2D.Double(bounds.x + 10, bounds.y + 2));
                }
            } else {
                if (up && right) {
                    frame = upRightSprite;
                } else if (up && left) {
                    frame = upLeftSprite;
                } else if (down && right) {
                    frame = downRightSprite;
                } else if (down && left) {
                    frame = downLeftSprite;
                } else if (up) {
                    frame = upSprite;
                } else if (down) {
                    frame = downSprite;
                } else if (left) {
                    frame = leftSprite;
                } else if (right) {
                    frame = rightSprite;
                }
            }
        } catch (Exception e) {

        }
    }
    private long timeAtLastShot = System.nanoTime();
    private void tryToShoot(double radians, Point2D.Double origin) throws Exception {
        if (System.nanoTime() - timeAtLastShot < 250000000) {
            return;
        }
        timeAtLastShot = System.nanoTime();
        game.add(new Bullet(game, bulletDir, origin, radians));
    }

    @Override
    public void render(Graphics2D g2d, Camera camera) {
        g2d.translate(bounds.x, bounds.y);
        g2d.setColor(new Color(0, 0, 0, 25));
        g2d.fillRect(3, 14, 6, 5);
        g2d.fillRect(2, 15, 8, 3);
        g2d.drawImage(frame, 0, 0, game);
        g2d.translate(-bounds.x, -bounds.y);
    }

}
