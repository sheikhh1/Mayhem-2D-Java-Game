package me.mayhem.screens.loadpage.items;

import me.mayhem.ui.AbstractInteractable;
import me.mayhem.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class LoadPageGameSelectButton extends ButtonInteractable {
    public LoadPageGameSelectButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Select save ");
    }

    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
