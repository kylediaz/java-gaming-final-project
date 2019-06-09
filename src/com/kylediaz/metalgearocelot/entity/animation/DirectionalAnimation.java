package com.kylediaz.metalgearocelot.entity.animation;

import com.kylediaz.metalgearocelot.util.Direction;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class DirectionalAnimation extends Animation {

    private Map<Direction, AnimationCycle> animationMap;

    private DirectionalAnimation(Map<Direction, AnimationCycle> animationMap, Direction initialDirection) {
        this.animationMap = animationMap;
        setCurrentDirection(initialDirection);
    }
    public static class Builder {
        private Map<Direction, AnimationCycle> animationMap = new HashMap<>();
        private Direction initialDirection = null;
        public Builder(Direction initialDirection) {
            this.initialDirection = initialDirection;
        }
        public Builder(){}
        public Builder add(Direction d, AnimationCycle a) {
            if (initialDirection == null) this.initialDirection = d;
            animationMap.put(d, a);
            return this;
        }
        public DirectionalAnimation build() {
            return new DirectionalAnimation(animationMap, initialDirection);
        }
    }

    /*
    the current animation is saved here so it doesn't have to search through the map every time it wants the animation
    using animationMap.get(currentDirection)
    If you ever want the current animation, use this variable. It should only be changed from one place - setCurrentDirection()
     */
    private AnimationCycle currentAnimation;
    private Direction currentDirection;

    @Override
    public BufferedImage currentFrame() {
        return currentAnimation.currentFrame();
    }
    public AnimationCycle getCurrentAnimation() {
        return currentAnimation;
    }
    public boolean isValidDirection(Direction direction) {
        return animationMap.containsKey(direction);
    }

    @Override
    public void cycle() {
        currentAnimation.cycle();
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
}
