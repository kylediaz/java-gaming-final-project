package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.entity.animation.Animation;
import com.kylediaz.metalgearocelot.entity.animation.AnimationCycle;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Entity {

    private Set<Animation> animations = new HashSet<>();

    public void draw(Graphics2D g) {

    }

    public void tick(double deltaTime) {
        animations.forEach(a -> a.cycle());
    }

    protected void addAnimation(Animation animation) {
        animations.add(animation);
    }

}
