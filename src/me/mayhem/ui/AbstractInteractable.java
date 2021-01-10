package me.mayhem.ui;

import me.mayhem.Mayhem;
import me.mayhem.input.InputManager;
import me.mayhem.input.impl.mouse.MouseInputListener;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

/**
 *
 * Skeletal implementation of the {@link Interatable} interface
 * Handles checking for if the {@link MouseEvent} occurs in the correct location
 *
 */
public abstract class AbstractInteractable extends MouseInputListener implements Interatable {

    private Shape shape;

    public AbstractInteractable(Shape shape) {
        super();

        this.shape = shape;

        this.register();
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

    /**
     * calls a load game page manager
     *
     * @param window the current renderwindow
     * @param event the event that has occoured
     */
    protected abstract void call(RenderWindow window, Event event);
}
