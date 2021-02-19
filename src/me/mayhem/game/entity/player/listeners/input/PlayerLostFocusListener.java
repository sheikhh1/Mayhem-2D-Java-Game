package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.screen.LostFocusListener;
import org.jsfml.window.event.Event;

/**
 * Handles events on key releases by the Player entity
 */
public class PlayerLostFocusListener extends LostFocusListener {

    private final Player player;

    public PlayerLostFocusListener(Player player) {
        this.player = player;
    }

    @Override
    protected void takeInput(Event event) {
        player.setState(EntityState.STANDING);
    }
}
