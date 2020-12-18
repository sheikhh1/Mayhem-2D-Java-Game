package me.mayhem.entity.sprites.player.listeners;

import me.mayhem.entity.sprites.player.Player;
import me.mayhem.input.impl.keyboard.KeyboardReleaseListener;
import org.jsfml.window.event.KeyEvent;

/**
 * Handles events on key releases by the Player entity
 */
public class PlayerKeyboardReleaseListener extends KeyboardReleaseListener {
    Player player;

    public PlayerKeyboardReleaseListener(Player player) {
        this.player = player;
    }

    @Override
    protected void takeInput(KeyEvent event) {
        switch (event.asKeyEvent().key) {
            case A:
            case D:
                player.setState(Player.playerState.STANDING);
                break;
        }

    }
}
