package com.kylediaz.metalgearocelot.entity.animation.directional;

/**
 * Only processes the current animation, making it faster
 */
public class QuickDirectionalAnimation extends DirectionalAnimation {
    private QuickDirectionalAnimation(Builder builder) {
        super(builder);
    }
    public static class Builder extends DirectionalAnimation.Builder<Builder> {
        public QuickDirectionalAnimation build() {
            return new QuickDirectionalAnimation(this);
        }
    }

    @Override
    public void cycle() {
        getCurrentAnimation().cycle();
    }

    @Override
    public boolean isFinished() {
        return getCurrentAnimation().isFinished();
    }
}
