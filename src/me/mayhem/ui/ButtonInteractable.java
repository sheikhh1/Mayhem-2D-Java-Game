package me.mayhem.ui;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ButtonInteractable extends AbstractInteractable {

    public ButtonInteractable(Shape shape) {
        super(shape);
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        System.out.println("HELLO");
    }
}
