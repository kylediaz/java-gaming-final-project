package com.kylediaz.metalgearocelot.entity.animation;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An ImageAnimation, but when it reaches the end of the animation, it loops back to the beginning
 */
public class AnimationCycle extends ImageAnimation {

    /**
     * @param cyclesPerFrame the number of times <code>currentFrame</code> will give the same <code>Image</code> until it switches to the next <code>Image</code>
     * @param frames         animation cycle composed of unique frames
     */
    public AnimationCycle(int cyclesPerFrame, BufferedImage... frames) {
        super(cyclesPerFrame, frames);
    }

    /**
     * Should be called in the <code>tick</code> method of the <code>Entity</code> when it wants to continue the animation cycle.
     * This was separated from <code>currentFrame</code> because currentFrame is called from <code>paint</code>, which has a varying tickrate
     * leading to variable animation speeds
     */
    @Override
    public void cycle() {
        cycles++;
        if (cycles == getCyclesPerFrame()) {
            currentFrame = (currentFrame + 1) % frames.length;
            cycles = 0;
        }
    }

}
