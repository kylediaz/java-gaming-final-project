package com.kylediaz.metalgearocelot.map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

    private final BufferedImage background, foreground;

    private Map(BufferedImage background, BufferedImage foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    public static class Builder {
        private BufferedImage background, foreground;

        public Builder background(BufferedImage image) {
            this.background = image;
            return this;
        }

        public Builder foreground(BufferedImage image) {
            this.foreground = image;
            return this;
        }

        public Map build() {
            return new Map(background, foreground);
        }
    }

    /**
     * Should be called before entities are drawn
     *
     * @param g2d Graphics2D var of the game it is in
     */
    public void drawBackground(Graphics2D g2d) {
        if (background != null) g2d.drawImage(background, 0, 0, null);
    }

    /**
     * should be called after entities are drawn
     *
     * @param g2d Graphics2D var of the game it is in
     */
    public void drawForeground(Graphics2D g2d) {
        if (foreground != null) g2d.drawImage(foreground, 0, 0, null);
    }

}