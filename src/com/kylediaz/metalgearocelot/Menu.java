package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.input.device.Keyboard;
import com.kylediaz.metalgearocelot.input.device.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {

    private Mouse mouse;
    private Keyboard keyboard;

    private JFrame parent;

    public Menu(JFrame parent) {
        this.parent = parent;
        mouse = new Mouse(parent) {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.viewGame();
            }
        };
        keyboard = new Keyboard(parent) {
            @Override
            public void keyTyped(KeyEvent e) {
                Main.viewGame();
            }
        };
    }

    @Override
    public void paint(Graphics g2d) {

    }
}
