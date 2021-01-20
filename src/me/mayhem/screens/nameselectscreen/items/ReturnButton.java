package me.mayhem.screens.nameselectscreen.items;

import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ReturnButton extends ButtonInteractable {
    public ReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return");
    }
    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
