package me.mayhem.ui;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class AbstractInteractable implements Interatable {

    private Shape shape;

    @Override
    public void onInteract(RenderWindow window, Event event) {
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = event.asMouseEvent();

            if (shape.getGlobalBounds().contains(mouseEvent.position.x, mouseEvent.position.y)) {
                this.call(window, event);
            }
        }
    }

    protected abstract void call(RenderWindow window, Event event);
}
