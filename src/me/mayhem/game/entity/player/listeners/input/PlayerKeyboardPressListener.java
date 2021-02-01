package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.keyboard.KeyboardPressListener;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
import me.mayhem.screens.gamescreen.GameScreenManager;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Keyboard input listener for the Player Entity
 */
public class PlayerKeyboardPressListener extends KeyboardPressListener {

    private Player player;
    private Map<Keyboard.Key, EntityState> keyHandler = new HashMap<>();

    public PlayerKeyboardPressListener(Player player) {
        this.player = player;

        this.keyHandler.put(Keyboard.Key.W, EntityState.JUMPING);
        this.keyHandler.put(Keyboard.Key.A, EntityState.BACK);
        this.keyHandler.put(Keyboard.Key.D, EntityState.FORWARD);
    }

    @Override
    protected void takeInput(KeyEvent event) {
        if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
            this.escapeHandler();
        } else {
            this.player.setState(this.keyHandler.get(event.asKeyEvent().key));
        }
    }

    private void escapeHandler() {
        Mayhem.getCurrentScreen().unloadScreen(Mayhem.getMainWindow());
        Mayhem.setCurrentScreen(new EscapeScreenManager(Mayhem.getMainWindow(), Mayhem.getCurrentScreen().getSound(), (GameScreenManager) Mayhem.getCurrentScreen()));
    }
}
