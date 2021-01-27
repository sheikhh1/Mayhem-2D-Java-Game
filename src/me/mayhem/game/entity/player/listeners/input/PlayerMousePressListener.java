package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.input.impl.mouse.MouseButtonPressListener;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.MouseButtonEvent;
/**
 * Mouse Listener for the Player Entity
 */
public class PlayerMousePressListener extends MouseButtonPressListener {

    Player player;

    public PlayerMousePressListener(Player player) {
        this.player = player;
    }

    @Override
    protected void takeInput(MouseButtonEvent event) {
        if (event.asMouseButtonEvent().button == Mouse.Button.LEFT) {
            this.player.setState(EntityState.MELEE);
            System.out.println("test 1");
        }
    }
}
