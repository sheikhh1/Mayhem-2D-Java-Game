package me.mayhem.game.entity.player.listeners;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.PlayerState;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardPressListener extends KeyboardPressListener {

    private Player player;
    private Map<Keyboard.Key, PlayerState> keyHandler = new HashMap<>();

    public PlayerKeyboardPressListener(Player player) {
        this.player = player;

        keyHandler.put(Keyboard.Key.W, PlayerState.JUMPING);
        keyHandler.put(Keyboard.Key.A, PlayerState.BACK);
        keyHandler.put(Keyboard.Key.D, PlayerState.FORWARD);
    }

    @Override
    protected void takeInput(KeyEvent event) {
        player.setState(keyHandler.get(event.asKeyEvent().key));
    }
}
