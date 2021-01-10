package me.mayhem.ui;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class AbstractInteractable extends InputListener<MouseEvent> implements Interatable {

    private Shape shape;

    public AbstractInteractable(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void register() {
        InputManager.registerInput(this);
    }

    @Override
    public void unregister() {
        InputManager.unregisterInput(this);
    }

    @Override
    protected void takeInput(MouseEvent event) {
        MouseEvent mouseEvent = event.asMouseEvent();

        if (shape.getGlobalBounds().contains(mouseEvent.position.x, mouseEvent.position.y)) {
            this.call(Mayhem.getMainWindow(), event);
        }
    }

    protected abstract void call(RenderWindow window, Event event);
}
