package com.kylediaz.metalgearocelot;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame window = new JFrame();

    public static void main(String[] args) {
        window.setSize(new Dimension(500, 500));
        viewGame(); //viewMenu();
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

}