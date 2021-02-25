package me.mayhem.screens.endgamescreen.items;

import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ReturnButton extends ButtonInteractable {
    public ReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Homepage ");
    }
    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
