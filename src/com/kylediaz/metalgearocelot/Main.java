package com.kylediaz.metalgearocelot;

import com.kylediaz.metalgearocelot.input.Input;
import com.kylediaz.metalgearocelot.input.device.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static Font PIXEL_FONT;
    static {
        try {
            PIXEL_FONT = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\metalgearocelot\\assets\\slkscr.ttf"));
            PIXEL_FONT = PIXEL_FONT.deriveFont((float) 7);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private static JFrame window = new JFrame();
    private static Keyboard keyboard = new Keyboard(window);

    public static void main(String[] args) {
        initInput();
        window.setSize(new Dimension(500, 500));
        viewGame();//viewMenu();
        window.setTitle("Metal Gear: Ocelot");
        window.setIconImage(new ImageIcon("src/com/kylediaz/metalgearocelot/assets/images/icon.png").getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    public static void viewMenu() {
        window.getContentPane().removeAll();
        window.getContentPane().add(new Menu(window));
    }
    public static void viewGame() {
        window.getContentPane().removeAll();
        window.getContentPane().add(new Game(window));

    }

    private static void initInput() {
        Input.setButton(InputIDs.UP, keyboard.new Button(KeyEvent.VK_UP));
        Input.setButton(InputIDs.DOWN, keyboard.new Button(KeyEvent.VK_DOWN));
        Input.setButton(InputIDs.LEFT, keyboard.new Button(KeyEvent.VK_LEFT));
        Input.setButton(InputIDs.RIGHT, keyboard.new Button(KeyEvent.VK_RIGHT));
        Input.setButton(InputIDs.SHOOT, keyboard.new Button(KeyEvent.VK_SPACE));
    }

}