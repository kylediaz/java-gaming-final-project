package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.camera.Camera;
import com.kylediaz.metalgearocelot.entity.EntityManager;
import com.kylediaz.metalgearocelot.entity.Snake;
import com.kylediaz.metalgearocelot.map.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel {

    private final EntityManager entityManager = new EntityManager();

    private Camera camera;

    public static Map map;
    static {
        try {
            map = new Map.Builder()
                    .background(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\maps\\arena\\background.png")))
                    .backgroundColor(new Color(0, 0, 32))
                    .occupiedArea(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\maps\\arena\\occupied areas.txt"))
                    .build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private Dimension targetDimension = new Dimension(64, 64);
    private WindowScaler windowScaler;

    private JFrame parent;

    public Game(JFrame parent) {
        this.parent = parent;
        initEntities();
        windowScaler = new WindowScaler(parent, targetDimension);
    }

    private void initEntities() {
        Snake snake = new Snake(16, 16);
        entityManager.add(snake);
        camera = new Camera(snake, targetDimension);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.transform(windowScaler.getTransformation());

        g2d.transform(camera.transformToFocus());

        map.drawBackground(g2d);
        entityManager.forEach(e -> e.draw(g2d));
        map.drawForeground(g2d);

        repaint();
    }

}
