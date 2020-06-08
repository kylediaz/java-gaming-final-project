package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.Game;
import com.kylediaz.metalgearocelot.InputIDs;
import com.kylediaz.metalgearocelot.entity.animation.*;
import com.kylediaz.metalgearocelot.entity.animation.directional.DirectionalAnimation;
import com.kylediaz.metalgearocelot.entity.animation.directional.QuickDirectionalAnimation;
import com.kylediaz.metalgearocelot.input.Input;
import com.kylediaz.metalgearocelot.util.Direction;
import com.kylediaz.metalgearocelot.util.Vector;
import com.kylediaz.metalgearocelot.util.geom.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Snake extends Soldier {

    protected final static DirectionalAnimation shootAnimation = createShootAnimations();
    private Move move;

    public Snake(double x, double y) {
        super(new Rectangle(x, y, 14, 18));
        move = new Move(createMovementAnimations());
        setDefaultEvent(move);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Image image = getCurrentEvent().getAnimation().currentFrame();
        g2d.drawImage(image, (int) Math.round(getBounds().x), (int) Math.round(getBounds().y), null);
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
        int speed = 25;
        int dX = 0, dY = 0;
        if (Input.getButton(InputIDs.UP))
            dY -= speed;
        if (Input.getButton(InputIDs.DOWN))
            dY += speed;
        if (Input.getButton(InputIDs.LEFT))
            dX -= speed;
        if (Input.getButton(InputIDs.RIGHT))
            dX += speed;

        setVelocity(Vector.rectangular(dX,dY));

        BufferedImage currentFrame = getCurrentEvent().getAnimation().currentFrame();
        double x = getBounds().x + getVelocity().getXComponent() * deltaTime;
        double y = getBounds().y + getVelocity().getYComponent() * deltaTime;
        if (Game.map.intersectionWithOccupiedArea(new Rectangle(x, y,
                currentFrame == null ? getBounds().width : currentFrame.getWidth(), currentFrame == null ? getBounds().height : currentFrame.getHeight())) != null)
            setVelocity(Vector.ZERO);


        if (Input.getButton(InputIDs.SHOOT) && !(getCurrentEvent() instanceof Shoot)) {

            setCurrentEvent(new Shoot(shootAnimation));
        }
    }

    private static MovementAnimation createMovementAnimations() {
        try {
            AnimationCycle upMove, downMove, leftMove, rightMove, downRightMove, downLeftMove, upLeftMove, upRightMove;
            upMove = new AnimationCycle(2,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\up\\8.png")));
            downMove = new AnimationCycle(2,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down\\8.png")));
            rightMove = new AnimationCycle(2,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\right\\8.png")));
            leftMove = new AnimationCycle(rightMove.getCyclesPerFrame(), rightMove.flipHorizontally());
            downRightMove = new AnimationCycle(2,
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\1.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\2.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\3.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\4.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\5.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\6.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\7.png")),
                    ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\move\\down right\\8.png")));
            upRightMove = new AnimationCycle(2,
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

            DirectionalAnimation moving = new QuickDirectionalAnimation.Builder().add(Direction.UP, upMove).add(Direction.DOWN, downMove)
                    .add(Direction.LEFT, leftMove).add(Direction.RIGHT, rightMove)
                    .add(Direction.DOWNRIGHT, downRightMove).add(Direction.DOWNLEFT, downLeftMove)
                    .add(Direction.UPRIGHT, upRightMove).add(Direction.UPLEFT, upLeftMove)
                    .build();

            DirectionalAnimation idle = new QuickDirectionalAnimation.Builder().add(Direction.UP, upIdle).add(Direction.DOWN, downIdle)
                    .add(Direction.LEFT, leftIdle).add(Direction.RIGHT, rightIdle)
                    .add(Direction.DOWNRIGHT, downRightIdle).add(Direction.DOWNLEFT, downLeftIdle)
                    .add(Direction.UPRIGHT, upRightIdle).add(Direction.UPLEFT, upLeftIdle)
                    .build();

            return new MovementAnimation.Builder().add(0, idle).add(25, moving).build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        return null;
    }
    private static DirectionalAnimation createShootAnimations() {
        try {
            Animation shootUp, shootDown, shootLeft, shootRight, shootUpLeft, shootUpRight, shootDownLeft, shootDownRight;
            shootUp = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\up.png")));
            shootDown = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\down.png")));
            shootLeft = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\left.png")));
            shootRight = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\right.png")));
            shootUpLeft = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\up left.png")));
            shootDownLeft = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\down left.png")));
            shootUpRight = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\up right.png")));
            shootDownRight = new ImageAnimation(10, ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\characters\\snake\\shoot\\down right.png")));
            return new DirectionalAnimation.Builder()
                    .add(Direction.UP, shootUp)
                    .add(Direction.DOWN, shootDown)
                    .add(Direction.LEFT, shootLeft)
                    .add(Direction.RIGHT, shootRight)
                    .add(Direction.DOWNRIGHT, shootDownRight)
                    .add(Direction.DOWNLEFT, shootDownLeft)
                    .add(Direction.UPRIGHT, shootUpRight)
                    .add(Direction.UPLEFT, shootUpLeft)
                    .build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        return null;
    }

}
