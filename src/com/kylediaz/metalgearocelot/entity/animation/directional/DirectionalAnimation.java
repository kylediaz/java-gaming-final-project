package com.kylediaz.metalgearocelot.entity.animation.directional;

import com.kylediaz.metalgearocelot.entity.animation.Animation;
import com.kylediaz.metalgearocelot.util.Direction;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * cycles animations in parallel
 */
public class DirectionalAnimation extends Animation {

    private final Map<Direction, Animation> animationMap;

    protected DirectionalAnimation(Builder<?> builder) {
        this.animationMap = builder.animationMap;
        setCurrentDirection(builder.initialDirection);
    }
    public static class Builder<T extends Builder<T>> {
        private Map<Direction, Animation> animationMap = new HashMap<>();
        private Direction initialDirection = null;
        public Builder(Direction initialDirection) {
            this.initialDirection = initialDirection;
        }
        public Builder(){}
        public Builder<T> add(Direction d, Animation a) {
            if (initialDirection == null)
                this.initialDirection = d;
            animationMap.put(d, a);
            return this;
        }
        public DirectionalAnimation build() {
            return new DirectionalAnimation(this);
        }
    }

    /*
    the current animation is saved here so it doesn't have to search through the map every time it wants the animation
    using animationMap.get(currentDirection)
    If you ever want the current animation, use this variable. It should only be changed from one place - setCurrentDirection()
     */
    private Animation currentAnimation;
    private Direction currentDirection;

    @Override
    public BufferedImage currentFrame() {
        return currentAnimation.currentFrame();
    }
    public Animation getCurrentAnimation() {
        return currentAnimation;
    }
    public boolean isValidDirection(Direction direction) {
        return animationMap.containsKey(direction);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(Direction direction) {
        if (animationMap.containsKey(direction)) {
            this.currentDirection = direction;
            this.currentAnimation = animationMap.get(direction);
        }
    }

    protected Map<Direction, Animation> getAnimations() {
        return animationMap;
    }

    @Override
    public void cycle() {
        getAnimations().values().forEach(Animation::cycle);
    }

    @Override
    public void reset() {
        getAnimations().values().forEach(Animation::reset);
    }

    @Override
    public boolean isFinished() {
        for (Animation a : getAnimations().values()) {
            if (a.isFinished())
                return true;
        }
        return false;
    }
}
