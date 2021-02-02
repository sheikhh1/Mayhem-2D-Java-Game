package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.mouse.MouseButtonReleaseListener;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.MouseButtonEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerMouseReleaseListener extends MouseButtonReleaseListener {

    private final Player player;
    private final Map<Mouse.Button, EntityState> mousePressReleaseHandler = new HashMap<>();

    public PlayerMouseReleaseListener(Player player) {
        this.player = player;
        this.mousePressReleaseHandler.put(Mouse.Button.LEFT, EntityState.NO_MOTION);
    }

    @Override
    protected void takeInput(MouseButtonEvent event) {
            if(this.player.isEntityGrounded()) {
                this.player.setState(this.mousePressReleaseHandler.get(event.asMouseButtonEvent().button));
            }
    }
}
