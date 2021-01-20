package me.mayhem.screens.escapescreen.items;

import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

import java.awt.*;

public class ReturnToGameButton extends ButtonInteractable {
    public ReturnToGameButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return to game ");
    }
    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
