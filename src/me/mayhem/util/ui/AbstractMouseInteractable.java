package me.mayhem.util.ui;

import me.mayhem.Mayhem;
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
public abstract class AbstractMouseInteractable extends MouseInputListener implements Interatable {

    protected Shape shape;

    /**
     *
     * Default constructor passing the shape to check the click location against
     *
     * @param shape The shape being displayed on the screen
     */
    public AbstractMouseInteractable(Shape shape) {
        super();

        this.shape = shape;
    }

    @Override
    protected void takeInput(MouseEvent event) {
        if (shape.getGlobalBounds().contains(event.position.x, event.position.y)) {
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

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(this.shape);
    }
}
