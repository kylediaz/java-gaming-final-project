package com.kylediaz.metalgearocelot.entity.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ImageAnimation extends Animation {

    private int cyclesPerFrame;

    protected int currentFrame = 0;
    protected BufferedImage[] frames;

    /**
     * @param cyclesPerFrame the number of times <code>currentFrame</code> will give the same <code>Image</code> until it switches to the next <code>Image</code>
     * @param frames animation cycle composed of unique frames
     */
    public ImageAnimation(int cyclesPerFrame, BufferedImage... frames) {
        this.cyclesPerFrame = cyclesPerFrame;
        this.frames = frames;
    }

    protected int cycles = 0;
    /**
     * Should be called in the <code>tick</code> method of the <code>Entity</code> when it wants to continue the animation cycle.
     * This was separated from <code>currentFrame</code> because currentFrame is called from <code>paint</code>, which has a varying tickrate
     * leading to variable animation speeds
     */
    public void cycle() {
        cycles++;
        if (cycles == cyclesPerFrame) {
            currentFrame = (currentFrame + 1);
            cycles = 0;
        }
    }
    public BufferedImage currentFrame() {
        if (currentFrame >= frames.length || currentFrame < 0)
            return null;
        else
            return frames[currentFrame];
    }

    public int getCyclesPerFrame() {
        return cyclesPerFrame;
    }
    public void setCyclesPerFrame(int cyclesPerFrame) {
        this.cyclesPerFrame = cyclesPerFrame;
    }

    public BufferedImage[] flipVertically() {
        BufferedImage[] outputFrames = new BufferedImage[frames.length];
        for (int i = 0; i < frames.length; i++) {
            outputFrames[i] = flipVertically(frames[i]);
        }
        return outputFrames;
    }
    private BufferedImage flipVertically(BufferedImage sprite){
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
    }
}
