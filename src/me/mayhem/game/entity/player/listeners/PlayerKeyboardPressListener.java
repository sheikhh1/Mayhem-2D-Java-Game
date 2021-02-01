package me.mayhem.game.entity.player.listeners;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.state.PlayerState;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
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
        if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
            escapeHandler();
        } else {
            player.setState(keyHandler.get(event.asKeyEvent().key));
        }
    }

    private void escapeHandler() {
        Mayhem.setCurrentScreen(new EscapeScreenManager(Mayhem.getMainWindow(), Mayhem.getCurrentScreen().getSound(), Mayhem.getCurrentScreen()));
    }
}
