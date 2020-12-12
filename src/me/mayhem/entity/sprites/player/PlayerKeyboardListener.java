package me.mayhem.entity.sprites.player;

import me.mayhem.input.impl.keyboard.KeyboardInputListener;
import org.jsfml.window.event.KeyEvent;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardListener extends KeyboardInputListener {

    @Override
    protected void takeInput(KeyEvent event) {
        System.out.println("Player Pressed:" + event.asKeyEvent().key);

        //TODO: Add functionality for W,A,S,D,Space..
        //TODO: Keys registering twice?
    }
}
