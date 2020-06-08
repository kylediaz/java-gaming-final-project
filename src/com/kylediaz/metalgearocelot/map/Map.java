package com.kylediaz.metalgearocelot.map;


import com.kylediaz.metalgearocelot.util.geom.Rectangle;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Map {

    private final BufferedImage background, foreground;
    private final Color backgroundColor;
    private final Collection<Rectangle> occupiedArea;

    private Map(Builder builder) {
        this.background = builder.background;
        this.foreground = builder.foreground;
        this.backgroundColor = builder.backgroundColor;
        this.occupiedArea = builder.occupiedArea;
    }

    public static class Builder {
        private BufferedImage background, foreground;
        private Color backgroundColor;
        private Collection<Rectangle> occupiedArea;

        public Builder background(BufferedImage image) {
            this.background = image;
            return this;
        }
        public Builder foreground(BufferedImage image) {
            this.foreground = image;
            return this;
        }
        public Builder backgroundColor(Color color) {
            this.backgroundColor = color;
            return this;
        }
        public Builder occupiedArea(File file) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                System.err.println(e);
                System.exit(1);
            }
            Set<Rectangle> output = new HashSet<>();
            reader.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] input = line.split(" ");
                    int x = Integer.parseInt(input[0]);
                    int y = Integer.parseInt(input[1]);
                    int width = Integer.parseInt(input[2]) - x;
                    int height = Integer.parseInt(input[3]) - y;
                    output.add(new Rectangle(x, y, width, height));
                }
            });
            this.occupiedArea = output;
            return this;
        }


        public Map build() {
            return new Map(this);
        }
    }

    /**
     * Should be called before entities are drawn
     *
     * @param g2d Graphics2D var of the game it is in
     */
    public void drawBackground(Graphics2D g2d) {
        Color prevColor = g2d.getColor();
        g2d.setColor(backgroundColor);
        g2d.fillRect(-512, -512, 1028, 1028);
        g2d.setColor(prevColor);
        if (background != null)
            g2d.drawImage(background, 0, 0, null);
    }

    /**
     * should be called after entities are drawn
     *
     * @param g2d Graphics2D var of the game it is in
     */
    public void drawForeground(Graphics2D g2d) {
        if (foreground != null)
            g2d.drawImage(foreground, 0, 0, null);
    }

    /**
     * @param rect
     * @return intersection with occupied area, null otherwise
     */
    public Rectangle intersectionWithOccupiedArea(Rectangle rect) {
        if (occupiedArea instanceof Collection == false || occupiedArea.size() == 0)
            return null;
        for (Rectangle occupiedArea : this.occupiedArea) {
            if (rect.intersects(occupiedArea)) {
                Rectangle2D intersection = rect.createIntersection(occupiedArea);
                return new Rectangle(intersection.getX(), intersection.getY(), intersection.getWidth(), intersection.getHeight());
            }
        }
        return null;
    }

}