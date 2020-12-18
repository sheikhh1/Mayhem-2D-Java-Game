package me.mayhem.entity.sprites.player.listeners;

import me.mayhem.entity.sprites.player.Player;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import org.jsfml.window.event.KeyEvent;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardPressListener extends KeyboardPressListener {

    private Player player;

    public PlayerKeyboardPressListener(Player player) {
        this.player = player;
    }

    @Override
    protected void takeInput(KeyEvent event) {
        //TODO: Add button functionality for:
        // Fight
        // Crouch?
        // S key
        // Pick-up
        // Pause / Show Menu

        switch(event.asKeyEvent().key){
            case W: // Replace with SPACE?
                player.setState(Player.playerState.JUMPING);
                break;
            case A:
                player.setState(Player.playerState.BACK);
                break;
            case S: // Pick up button?
                System.out.println("S is not mapped to any functionality ");
                break;
            case D:
                player.setState(Player.playerState.FORWARD);
                break;
        }
    }
}
