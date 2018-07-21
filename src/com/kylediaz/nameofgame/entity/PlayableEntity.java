package com.kylediaz.nameofgame.entity;

import com.kylediaz.nameofgame.Game;
import com.kylediaz.nameofgame.input.Mouse;
import java.io.File;
import java.util.ArrayList;

/**
 * Unlike Entity, PlayableEntity asks for pointers to the Mouse and Keyboard
 * @author Kyle Diaz
 */
public abstract class PlayableEntity extends Entity {
    
    protected ArrayList<Integer> pressedKeys;
    protected Mouse mouse;
    
    public PlayableEntity(Game game, File directory, ArrayList<Integer> keyboard, Mouse mouse) {
        super(game, directory);
        this.pressedKeys = keyboard;
        this.mouse = mouse;
    }
    
}
