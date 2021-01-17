package me.mayhem.game.entity.player.listeners;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
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
    private final Map<Keyboard.Key, EntityState> keyReleaseHandler = new HashMap<>();

    public PlayerKeyboardReleaseListener(Player player) {
        this.player = player;

        this.keyReleaseHandler.put(Keyboard.Key.A, EntityState.STANDING);
        this.keyReleaseHandler.put(Keyboard.Key.D, EntityState.STANDING);
    }

    @Override
    protected void takeInput(KeyEvent event) {
        player.setState(this.keyReleaseHandler.get(event.asKeyEvent().key));
    }
}
