package me.mayhem.entity.sprites.player.listeners;

import me.mayhem.entity.sprites.player.Player;
import me.mayhem.entity.sprites.player.PlayerState;
import me.mayhem.input.impl.keyboard.KeyboardReleaseListener;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles events on key releases by the Player entity
 */
public class PlayerKeyboardReleaseListener extends KeyboardReleaseListener {

    private final Player player;
    private final Map<Keyboard.Key, PlayerState> keyReleaseHandler = new HashMap<>();

    public PlayerKeyboardReleaseListener(Player player) {
        this.player = player;

        keyReleaseHandler.put(Keyboard.Key.A, PlayerState.STANDING);
        keyReleaseHandler.put(Keyboard.Key.D, PlayerState.STANDING);
    }

    @Override
    protected void takeInput(KeyEvent event) {
        player.setState(keyReleaseHandler.get(event.asKeyEvent().key));
    }
}
