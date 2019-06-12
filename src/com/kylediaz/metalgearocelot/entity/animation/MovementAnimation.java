package com.kylediaz.metalgearocelot.entity.animation;

import com.kylediaz.metalgearocelot.entity.animation.directional.DirectionalAnimation;
import com.kylediaz.metalgearocelot.util.Direction;
import com.kylediaz.metalgearocelot.util.Vector;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MovementAnimation extends Animation {

    /*
    The map needs to be sorted for the getDirectionalAnimation method.
     */
    private SortedMap<Double, DirectionalAnimation> animations;

    private double currentSpeed;
    private Direction currentDirection;

    // Prevents outside instantiation
    protected MovementAnimation(SortedMap<Double, DirectionalAnimation> animations) {
        this.animations = animations;
    }

    public static class Builder {
        private SortedMap<Double, DirectionalAnimation> animations = new TreeMap<>();
        public Builder add(double speed, DirectionalAnimation animation) {
            animations.put(speed, animation);
            return this;
        }
        public MovementAnimation build() {
            if (animations.size() == 0) {
                throw new RuntimeException("Movement Animation with no animations");
            }
            return new MovementAnimation(animations);
        }
    }

    @Override
    public BufferedImage currentFrame() {
        return currentAnimation().currentFrame();
    }
    public Animation currentAnimation() {
        DirectionalAnimation animation = getDirectionalAnimation();
        animation.setCurrentDirection(currentDirection);
        return animation.getCurrentAnimation();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public double getCurrentSpeed() {
        return currentSpeed;
    }
    public void setVelocity(Vector v) {
        currentSpeed = v.magnitude();
        try {
            // 0 degrees and not moving appear to be the same thing, so I have to make sure it is moving
            if (v != Vector.ZERO) {
                Direction direction = v.getDirection();
                if (direction == currentDirection || getDirectionalAnimation().isValidDirection(direction))
                    currentDirection = direction;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    private DirectionalAnimation getDirectionalAnimation() {
        DirectionalAnimation output = null;
        for (Map.Entry<Double, DirectionalAnimation> entry : animations.entrySet()) {
            if (currentSpeed <= entry.getKey())
                return entry.getValue();
            output = entry.getValue();
        }
        return output;
    }

    @Override
    public void cycle() {
        getDirectionalAnimation().cycle();
    }

    @Override
    public void reset() {
        animations.values().forEach(DirectionalAnimation::reset);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}