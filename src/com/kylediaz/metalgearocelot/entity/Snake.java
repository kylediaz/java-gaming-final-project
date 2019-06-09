package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.AnimationCycle;
import com.kylediaz.metalgearocelot.entity.animation.MovementAnimation;
import com.kylediaz.metalgearocelot.entity.animation.SingleFrameAnimationCycle;
import com.kylediaz.metalgearocelot.util.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Snake extends PhysicalEntity {

    private final static double WIDTH = 8, HEIGHT = 8;

    public Snake(double x, double y) {
        super(new Rectangle2D.Double(x, y, WIDTH, HEIGHT));
        createMovementAnimations();
        addAnimation(movementAnimation);
    }

    MovementAnimation movementAnimation;

    @Override
    public void draw(Graphics2D g2d) {
        try {
            // 0 degrees and not moving appear to be the same thing, so I have to make sure it is moving
            if (getVelocity() != Vector.ZERO)
                movementAnimation.setCurrentDirection(getVelocity().getDirection());
            movementAnimation.setMoving(getVelocity() != Vector.ZERO);
        } catch (Exception e) {
            System.err.println(e);
        }
        Image image = movementAnimation.currentFrame();
        g2d.drawImage(image, (int) getBounds().x, (int) getBounds().y, null);
    }

    private void createMovementAnimations() {
        try {
            AnimationCycle moveUp, moveDown, moveLeft, moveRight;
            moveUp = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\8.png")));
            moveDown = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\8.png")));
            moveRight = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\8.png")));
            moveLeft = new AnimationCycle(moveRight.getCyclesPerFrame(), moveRight.flipVertically());

            AnimationCycle upIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up\\1.png")));
            AnimationCycle downIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down\\1.png")));
            AnimationCycle leftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\left\\1.png")));
            AnimationCycle rightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\right\\1.png")));

            AnimationCycle upLeftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up left\\1.png")));
            AnimationCycle upRightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up right\\1.png")));
            AnimationCycle downLeftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down left\\1.png")));
            AnimationCycle downRightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down right\\1.png")));

            movementAnimation = new MovementAnimation.Builder().cardinal(moveRight,moveDown,moveLeft,moveUp, rightIdle, downIdle, leftIdle, upIdle)
                    .diagonal(moveDown, moveDown, moveUp, moveUp, downRightIdle, downLeftIdle, upLeftIdle, upRightIdle).build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

}
