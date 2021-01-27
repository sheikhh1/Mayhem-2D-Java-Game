package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.mouse.MouseButtonReleaseListener;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.MouseButtonEvent;

public class PlayerMouseReleaseListener extends MouseButtonReleaseListener {

    private Player player;

    public PlayerMouseReleaseListener(Player player) {
        this.player = player;
    }

    @Override
    protected void takeInput(MouseButtonEvent event) {
        if (event.asMouseButtonEvent().button == Mouse.Button.LEFT) {
            //this.player.setState(EntityState.STANDING);
        }

    }
}
