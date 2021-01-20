package me.mayhem.util.ui;

import me.mayhem.input.InputListener;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseEvent;

/**
 *
 * Skeletal implementation of the {@link Interatable} interface
 * Handles checking for if the {@link MouseEvent} occurs in the correct location
 *
 */
public abstract class AbstractKeyboardMouseInteractable extends InputListener<KeyEvent> implements Interatable {

    protected Shape shape;
    private boolean focussed;

    public AbstractKeyboardMouseInteractable(Shape shape) {
        super(Event.Type.KEY_PRESSED, Event.Type.MOUSE_BUTTON_PRESSED);

        this.shape = shape;
    }

    @Override
    public void onEvent(Event event) {
        if (!super.getTypes().contains(event.type)) {
            return;
        }

        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = event.asMouseEvent();

            this.focussed = shape.getGlobalBounds().contains(mouseEvent.position.x, mouseEvent.position.y);
        }

        if (this.focussed && (event instanceof KeyEvent)) {
            this.takeInput(event.asKeyEvent());
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        renderWindow.draw(this.shape);
    }
}
