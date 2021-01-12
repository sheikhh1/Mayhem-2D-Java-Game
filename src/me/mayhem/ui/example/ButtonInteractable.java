package me.mayhem.ui.example;

import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ButtonInteractable extends AbstractInteractable {

    public ButtonInteractable(Shape shape) {
        super(shape);
    }

    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
