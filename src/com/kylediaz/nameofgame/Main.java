package com.kylediaz.nameofgame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1000, 1000));
        window.setMinimumSize(new Dimension(20, 20));

        // Hides the mouse pointer
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        window.getContentPane().setCursor(blankCursor);

        window.setIconImage(new ImageIcon(System.getProperty("user.dir") + "/src/com/kylediaz/nameofgame/resources/images/icon.png").getImage());
        window.setTitle("NameOfGame");
        
        window.add(new Game(window));
        
        window.setVisible(true);
    }
    
}