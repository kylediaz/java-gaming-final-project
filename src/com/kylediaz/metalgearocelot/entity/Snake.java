package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.AnimationCycle;
import com.kylediaz.metalgearocelot.entity.animation.DirectionalAnimation;
import com.kylediaz.metalgearocelot.entity.animation.MovementAnimation;
import com.kylediaz.metalgearocelot.entity.animation.SingleFrameAnimationCycle;
import com.kylediaz.metalgearocelot.util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Snake extends PhysicalEntity {

    public Snake(double x, double y) {
        super(new Rectangle2D.Double(x, y, 14, 18));
        createMovementAnimations();
        addAnimation(movementAnimation);
    }

    MovementAnimation movementAnimation;

    @Override
    public void draw(Graphics2D g2d) {
        try {
            movementAnimation.setVelocity(getVelocity());
        } catch (Exception e) {
            System.err.println(e);
        }
        Image image = movementAnimation.currentFrame();
        g2d.drawImage(image, (int) Math.round(getBounds().x), (int) Math.round(getBounds().y), null);
    }

    private void createMovementAnimations() {
        try {
            AnimationCycle upMove, downMove, leftMove, rightMove, downRightMove, downLeftMove, upLeftMove, upRightMove;
            upMove = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\8.png")));
            downMove = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\8.png")));
            rightMove = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\8.png")));
            leftMove = new AnimationCycle(rightMove.getCyclesPerFrame(), rightMove.flipHorizontally());
            downRightMove = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\8.png")));
            upRightMove = new AnimationCycle(8,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up right\\8.png")));
            downLeftMove = new AnimationCycle(downRightMove.getCyclesPerFrame(), downRightMove.flipHorizontally());
            upLeftMove = new AnimationCycle(upRightMove.getCyclesPerFrame(), upRightMove.flipHorizontally());

            AnimationCycle upIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up\\1.png")));
            AnimationCycle downIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down\\1.png")));
            AnimationCycle leftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\left\\1.png")));
            AnimationCycle rightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\right\\1.png")));

            AnimationCycle upLeftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up left\\1.png")));
            AnimationCycle upRightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\up right\\1.png")));
            AnimationCycle downLeftIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down left\\1.png")));
            AnimationCycle downRightIdle = new SingleFrameAnimationCycle(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\idle\\down right\\1.png")));

            DirectionalAnimation moving = new DirectionalAnimation.Builder().add(Direction.UP, upMove).add(Direction.DOWN, downMove)
                    .add(Direction.LEFT, leftMove).add(Direction.RIGHT, rightMove)
                    .add(Direction.DOWNRIGHT, downRightMove).add(Direction.DOWNLEFT, downLeftMove)
                    .add(Direction.UPRIGHT, upRightMove).add(Direction.UPLEFT, upLeftMove)
                    .build();

            DirectionalAnimation idle = new DirectionalAnimation.Builder().add(Direction.UP, upIdle).add(Direction.DOWN, downIdle)
                    .add(Direction.LEFT, leftIdle).add(Direction.RIGHT, rightIdle)
                    .add(Direction.DOWNRIGHT, downRightIdle).add(Direction.DOWNLEFT, downLeftIdle)
                    .add(Direction.UPRIGHT, upRightIdle).add(Direction.UPLEFT, upLeftIdle)
                    .build();

            movementAnimation = new MovementAnimation.Builder().add(0, idle).add(25, moving).build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

}
