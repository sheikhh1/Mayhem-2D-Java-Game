package me.mayhem.screens.escapescreen.items;

import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SaveGameButton extends ButtonInteractable {
    public SaveGameButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Save");
    }
    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
