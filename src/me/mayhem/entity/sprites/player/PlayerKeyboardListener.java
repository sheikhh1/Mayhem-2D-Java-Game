package me.mayhem.entity.sprites.player;

import me.mayhem.input.impl.keyboard.KeyboardInputListener;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardListener extends KeyboardPressListener {

    @Override
    protected void takeInput(KeyEvent event) {
        //TODO: Add functionality for W,A,S,D
        // Fight Button?


        switch(event.asKeyEvent().key){
            case W: // Replace with SPACE?
                System.out.println("Jump");
                break;
            case A:
                System.out.println("Move Back");
                break;
            case S: // Pick up button?
                System.out.println("S is pressed");
                break;
            case D:
                System.out.println("Move Forward");
                break;
        }
    }
}
