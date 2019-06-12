package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.camera.Camera;
import com.kylediaz.metalgearocelot.entity.EntityManager;
import com.kylediaz.metalgearocelot.entity.Snake;
import com.kylediaz.metalgearocelot.input.Keyboard;
import com.kylediaz.metalgearocelot.map.Map;
import com.kylediaz.metalgearocelot.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel {

    private Keyboard keyboard;

    private EntityManager entityManager = new EntityManager();
    private Camera camera;

    private Map map;
    {
        try {
            map = new Map.Builder()
                    .background(ImageIO.read(new File("src\\com\\kylediaz\\metalgearocelot\\assets\\maps\\arena\\background.png")))
                    .backgroundColor(new Color(0, 0, 32))
                    .build();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private WindowScaler windowScaler = new WindowScaler();

    private JFrame parent;

    public Game(JFrame parent) {
        this.parent = parent;
        keyboard = new Keyboard(parent);

        initEntities();

        // listen for window resizing
        parent.addComponentListener(windowScaler);
    }

    private void initEntities() {
        Snake snake = new Snake(16, 16) {
            @Override
            public void tick(double deltaTime) {
                super.tick(deltaTime);
                int speed = 25;
                int x = 0, y = 0;
                if (keyboard.isPressed(KeyEvent.VK_UP))
                    y -= speed;
                if (keyboard.isPressed(KeyEvent.VK_DOWN))
                    y += speed;
                if (keyboard.isPressed(KeyEvent.VK_LEFT))
                    x -= speed;
                if (keyboard.isPressed(KeyEvent.VK_RIGHT))
                    x += speed;
                setVelocity(Vector.rectangular(x, y));
            }
        };
        entityManager.add(snake);
        camera = new Camera(snake, targetDimension);
    }

    private Dimension targetDimension = new Dimension(64, 64);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.transform(windowScaler.getTransformation());

        g2d.transform(camera.transformToFocus());

        map.drawBackground(g2d);
        entityManager.forEach(e -> e.draw(g2d));
        map.drawForeground(g2d);

        repaint();
    }

    private class WindowScaler extends ComponentAdapter {

        private double scale = 1.0;
        private double paddingLeft, paddingTop;

        public void componentResized(ComponentEvent componentEvent) {
            scale = Math.min((float) parent.getWidth() / camera.getDimension().width, (float) parent.getHeight() / camera.getDimension().height);
            paddingLeft = (parent.getWidth() - camera.getDimension().width * scale) / 2 / scale;
            paddingTop = (parent.getHeight() - camera.getDimension().height * scale) / 2 / scale;
        }

        private AffineTransform getTransformation() {
            AffineTransform output = new AffineTransform();
            output.scale(scale, scale);
            output.translate(paddingLeft, paddingTop);
            return output;
        }
    }

}
