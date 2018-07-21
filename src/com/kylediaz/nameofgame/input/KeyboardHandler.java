package com.kylediaz.nameofgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
 * Keeps track of which keys are currently pressed
 * @author Kyle Diaz
 */
public class KeyboardHandler implements KeyListener {
    
    // Using Integer instead of characters because some keys dont have chars (ctrl, arrow keys, ect)
    private final ArrayList<Integer> pressedKeys = new ArrayList<>();
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode()))
            pressedKeys.add((Integer) e.getKeyCode());
    }
    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
    }
    
    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }
    
    // Unused overrided method
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
