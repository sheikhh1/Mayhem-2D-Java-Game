package me.mayhem.entity.sprites.player.listeners;

import me.mayhem.entity.sprites.player.Player;
import me.mayhem.input.impl.keyboard.KeyboardReleaseListener;
import org.jsfml.window.event.KeyEvent;

/**
 * Handles events on key releases by the Player entity
 */
public class PlayerKeyboardReleaseListener extends KeyboardReleaseListener {
    Player player;

    public PlayerKeyboardReleaseListener(Player p){
        player = p;
    }

    @Override
    protected void takeInput(KeyEvent event) {

    }
}
