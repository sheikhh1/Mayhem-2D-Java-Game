package me.mayhem.game.entity.player.listeners.input;

import me.mayhem.game.entity.player.Player;
import me.mayhem.input.impl.mouse.MouseButtonPressListener;
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
        System.out.println("Player has pressed the Mouse");
    }
}
