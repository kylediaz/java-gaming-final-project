package com.kylediaz.metalgearocelot.entity.animation;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A single frame animation that doesn't end
 */
public class SingleFrameAnimationCycle extends AnimationCycle {

    public SingleFrameAnimationCycle(BufferedImage frame) {
        super(Integer.MAX_VALUE, new BufferedImage[]{frame});
    }

}
