package com.kylediaz.nameofgame;

import com.kylediaz.nameofgame.Game;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Kyle Diaz
 */
public class Map {
    
    public final String NAME;
    
    public BufferedImage background;
    public BufferedImage foreground;
    public ArrayList<BufferedImage> dataLayers = new ArrayList<>();
    
    public Dimension dimension = null;
    
    public Map(File filePath) throws IOException {
        
        this.NAME = filePath.getName();
        
        ArrayList<File> files = new ArrayList<>(Arrays.asList(filePath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".png") || fileName.endsWith(".txt");
            }
        })));
        Game.LOGGER.log(Level.INFO, "Map " + NAME + " is using files " + files);
        
        for (File file : files) {
            if (file.getName().equals("background.png")) {
                this.background = ImageIO.read(file);
                Game.LOGGER.log(Level.INFO, "Map " + NAME + " is using file " + file.getName() + " as a background");
            } else if (file.getName().equals("foreground.png")) {
                this.foreground = ImageIO.read(file);
            } else if (file.getName().contains("data")) {
                dataLayers.add(ImageIO.read(file));
            } else if (file.getName().equals("meta.txt")) {
                try {
                    getData(file);
                } catch (Exception ex) {
                    Game.LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            } else {
                Game.LOGGER.log(Level.WARNING, "File " + file + " is not being used");
            }
        }
    }
    private final static int COMMAND = 0, ARGUEMENT = 1;
    private void getData(File file) throws Exception {
        int width = -1, height = -1;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase().trim();
            line = line.replace(" ", "");
            String[] components = line.split(":");
            if (components[COMMAND].equals("width")) {
                width = Integer.parseInt(components[ARGUEMENT]);
            } else if (components[COMMAND].equals("height")) {
                height = Integer.parseInt(components[ARGUEMENT]);
            } else {
                throw new Exception("unrecognized command");
            }
        }
        if (width <= 0 || height <= 0) {
            throw new Exception("Unusable width or height value");
        } else {
            this.dimension = new Dimension(width, height);
            Game.LOGGER.log(Level.INFO, "Map " + NAME + " data: width- " + dimension.width + " height- " + dimension.height);
        }
    }

}