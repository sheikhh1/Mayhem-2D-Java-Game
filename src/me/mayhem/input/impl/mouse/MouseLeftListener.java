package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

/**
 *
 * Concrete implementation of the {@link InputListener} to remove any generics from higher level code
 * thus making it easier for the team to use listeners as all the potential ambiguity and confusion is removed.
 *
 * This implementation is for the event where the mouse leaves the screen area
 *
 */
public abstract class MouseLeftListener extends InputListener<MouseEvent> {

    /**
     *
     * Default constructor passing the correct {@link Event.Type} that relate to this listener
     *
     */
    public MouseLeftListener() {
        super(Event.Type.MOUSE_LEFT);
    }
}
