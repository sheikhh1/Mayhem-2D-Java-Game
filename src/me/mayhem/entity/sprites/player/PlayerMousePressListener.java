package me.mayhem.entity.sprites.player;

import me.mayhem.input.impl.mouse.MouseButtonPressListener;
import org.jsfml.window.event.MouseButtonEvent;

/**
 * Mouse Listener for the Player Entity
 */
public class PlayerMousePressListener extends MouseButtonPressListener {

    @Override
    protected void takeInput(MouseButtonEvent event) {
        System.out.println("Player has pressed the Mouse");
    }
}
