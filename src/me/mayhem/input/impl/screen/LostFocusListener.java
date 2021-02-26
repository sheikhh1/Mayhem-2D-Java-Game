package me.mayhem.input.impl.screen;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of the {@link InputListener} to remove any generics from higher level code
 * thus making it easier for the team to use listeners as all the potential ambiguity and confusion is removed.
 *
 * This implementation is for the event where the user clicks out of the screen
 *
 */
public abstract class LostFocusListener extends InputListener<Event> {

    /**
     *
     * Default constructor passing the correct {@link Event.Type} that relate to this listener
     *
     */
    public LostFocusListener() {
        super(Event.Type.LOST_FOCUS);
    }
}
