package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.input.Input;
import com.kylediaz.metalgearocelot.input.device.Keyboard;
import com.kylediaz.metalgearocelot.input.device.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {

    private BufferedImage backgroundImage;

    private WindowScaler windowScaler;

    private JFrame parent;

    public Menu(JFrame parent) {
        this.parent = parent;
        try {
            backgroundImage = ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\images\\main menu background.png"));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        this.setBackground(new Color(248, 0, 0)); // background color of image
        windowScaler = new WindowScaler(parent, new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight()));
    }

    private int count = 0;
    private final int MAX_COUNT = 60;
    private boolean showText = true;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Input.buttons().stream().anyMatch(b -> b.get()))
            Main.viewGame();
        Graphics2D g2d = (Graphics2D) g;
        g2d.transform(windowScaler.getTransformation());
        g2d.drawImage(backgroundImage, 0, 0, null);

        if (showText) {
            g2d.setFont(Main.PIXEL_FONT);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Press any button", 40, 88);
        }
        count++;
        if (count >= MAX_COUNT) {
            count = 0;
            showText = !showText;
        }

        repaint();
    }
}
