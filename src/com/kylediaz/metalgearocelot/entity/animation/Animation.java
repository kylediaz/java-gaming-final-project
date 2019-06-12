package com.kylediaz.metalgearocelot.entity.animation;

import java.awt.image.BufferedImage;

public abstract class Animation {

    public abstract BufferedImage currentFrame();

    public abstract void cycle();

    public abstract void reset();

    public abstract boolean isFinished();

    @Override
    public String toString() {
        return currentFrame() != null ? String.format("%s on frame %s", super.toString(), currentFrame().toString())
                : super.toString() + " Animation done";
    }

}
