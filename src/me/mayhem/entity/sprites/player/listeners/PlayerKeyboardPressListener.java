package me.mayhem.entity.sprites.player.listeners;

import me.mayhem.entity.sprites.player.Player;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import org.jsfml.window.event.KeyEvent;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardPressListener extends KeyboardPressListener {

    private Player player;

    public PlayerKeyboardPressListener(Player p){
        this.player = p;
    }

    @Override
    protected void takeInput(KeyEvent event) {
        //TODO: Add functionality for W,A,S,D
        // Fight Button?

        switch(event.asKeyEvent().key){
            case W: // Replace with SPACE?
                player.setKeyPress(1);
                System.out.println("Jump");
                //player.setVelY(-5f);
                break;
            case A:
                System.out.println("Move Back");
                player.setVelX(-5f);
                break;
            case S: // Pick up button?
                System.out.println("S is pressed");
                player.setVelY(5f);
                break;
            case D:
                System.out.println("Move Forward");
                player.setVelX(5f);
                break;
        }
    }
}
