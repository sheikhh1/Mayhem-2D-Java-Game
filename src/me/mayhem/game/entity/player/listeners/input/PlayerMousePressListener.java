package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.mouse.MouseButtonPressListener;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.MouseButtonEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Mouse Listener for the Player Entity
 */
public class PlayerMousePressListener extends MouseButtonPressListener {

    private final Player player;
    private final Map<Mouse.Button, EntityState> mousePressHandler = new HashMap<>();

    public PlayerMousePressListener(Player player) {
        this.player = player;

        this.mousePressHandler.put(Mouse.Button.LEFT, EntityState.MELEE);
        this.mousePressHandler.put(Mouse.Button.RIGHT, EntityState.RANGEATTACK);
    }

    @Override
    protected void takeInput(MouseButtonEvent event) {
        if(this.player.isEntityGrounded()) {
            this.player.setState(this.mousePressHandler.get(event.asMouseButtonEvent().button));
        }
    }
}
