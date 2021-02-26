package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

/**
 *
 * Concrete implementation of the {@link InputListener} to remove any generics from higher level code
 * thus making it easier for the team to use listeners as all the potential ambiguity and confusion is removed.
 *
 * This implementation is for the event where the user presses a button on their mouse
 *
 */
public abstract class MouseButtonPressListener extends InputListener<MouseButtonEvent> {

    /**
     *
     * Default constructor passing the correct {@link Event.Type} that relate to this listener
     *
     */
    public MouseButtonPressListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED);
    }
}
