package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.camera.Camera;
import com.kylediaz.metalgearocelot.entity.EntityManager;
import com.kylediaz.metalgearocelot.entity.Snake;
import com.kylediaz.metalgearocelot.input.Keyboard;
import com.kylediaz.metalgearocelot.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

public class Game extends JPanel {

    private Keyboard keyboard;

    private EntityManager entityManager = new EntityManager();
    private Camera camera;

    WindowScaler windowScaler = new WindowScaler();

    private JFrame parent;

    public Game(JFrame parent) {
        this.parent = parent;
        keyboard = new Keyboard(parent);

        initEntities();

        // this listens for the window resizing - and when it does it changes the scale of the game to match the new size
        parent.addComponentListener(windowScaler);
    }
    private void initEntities() {
        Snake snake = new Snake(0, 0) {
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
        windowScaler.scaleToFitWindow(g2d);

        g2d.setColor(Color.WHITE);
        g2d.setColor(Color.RED);
        entityManager.forEach(e -> e.draw(g2d));
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
        private void scaleToFitWindow(Graphics2D g2d) {
            g2d.scale(scale, scale);
            g2d.translate(paddingLeft, paddingTop);
        }
    }

}
