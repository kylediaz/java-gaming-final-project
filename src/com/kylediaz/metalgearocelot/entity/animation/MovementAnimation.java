package com.kylediaz.metalgearocelot.entity.animation;

import com.kylediaz.metalgearocelot.util.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class MovementAnimation extends Animation {

    // Array indices
    private static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;
    private static final int DOWNRIGHT = 0, DOWNLEFT = 1, UPLEFT = 2, UPRIGHT = 3;
    
    private AnimationCycle[] crossMoving, crossIdle, diagonalMoving, diagonalIdle;
    private Direction currentDirection = Direction.RIGHT;
    private boolean isMoving = false;

    // Prevents outside instantiation
    private MovementAnimation(AnimationCycle[] crossMoving, AnimationCycle[] crossIdle, AnimationCycle[] diagonalMoving, AnimationCycle[] diagonalIdle) {
        this.crossMoving = crossMoving;
        this.crossIdle = crossIdle;
        this.diagonalMoving = diagonalMoving;
        this.diagonalIdle = diagonalIdle;
    }

    public static class Builder {
        private AnimationCycle[] crossMoving, crossIdle, diagonalMoving, diagonalIdle;
        public Builder cardinal(AnimationCycle rightMoving, AnimationCycle downMoving, AnimationCycle leftMoving, AnimationCycle upMoving,
                                AnimationCycle rightIdle, AnimationCycle downIdle, AnimationCycle leftIdle, AnimationCycle upIdle) {
            crossMoving = new AnimationCycle[] {rightMoving, downMoving, leftMoving, upMoving};
            crossIdle = new AnimationCycle[] {rightIdle,downIdle,leftIdle,upIdle};
            return this;
        }
        public Builder diagonal(AnimationCycle downRightMoving, AnimationCycle downLeftMoving, AnimationCycle upLeftMoving, AnimationCycle upRightMoving,
                                AnimationCycle downRightIdle, AnimationCycle downLeftIdle, AnimationCycle upLeftIdle, AnimationCycle upRightIdle) {
            diagonalMoving = new AnimationCycle[] {downRightMoving, downLeftMoving, upLeftMoving, upRightMoving};
            diagonalIdle = new AnimationCycle[] {downRightIdle,downLeftIdle, upLeftIdle,upRightIdle};
            return this;
        }
        public MovementAnimation build() {
            return new MovementAnimation(crossMoving, crossIdle, diagonalMoving, diagonalIdle);
        }
    }

    @Override
    public BufferedImage currentFrame() {
        return currentAnimationCycle().currentFrame();
    }
    public AnimationCycle currentAnimationCycle() {
        AnimationCycle[] crossArr, diagonalArr;
        if (isMoving) {
            crossArr = crossMoving;
            diagonalArr = diagonalMoving;
        } else {
            crossArr = crossIdle;
            diagonalArr = diagonalIdle;
        }
        AnimationCycle output;
        System.out.println(currentDirection);
        switch (currentDirection) {
            case RIGHT:
                output = crossArr[RIGHT];
                break;
            case DOWN:
                output = crossArr[DOWN];
                break;
            case LEFT:
                output = crossArr[LEFT];
                break;
            case UP:
                output = crossArr[UP];
                break;
            case DOWNRIGHT:
                output = diagonalArr[DOWNRIGHT];
                break;
            case DOWNLEFT:
                output = diagonalArr[DOWNLEFT];
                break;
            case UPLEFT:
                output = diagonalArr[UPLEFT];
                break;
            default: //case UPRIGHT:
                output = diagonalArr[UPRIGHT];
        }
        return output;
    }

    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(Direction direction) {
        if (!isValidDirection(direction))
            throw new RuntimeException("Illegal direction");
        else
            this.currentDirection = direction;
    }
    public boolean isValidDirection(Direction direction) {
        return (direction.isCardinal() && crossMoving instanceof AnimationCycle[] && crossIdle instanceof AnimationCycle[])
                || (direction.isDiagonal() && diagonalMoving instanceof  AnimationCycle[] && diagonalIdle instanceof AnimationCycle[]);
    }

    @Override
    public void cycle() {
        currentAnimationCycle().cycle();
    }

}