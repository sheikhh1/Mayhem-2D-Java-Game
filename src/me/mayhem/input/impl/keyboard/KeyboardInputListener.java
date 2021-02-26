package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

/**
 *
 * Concrete implementation of the {@link InputListener} to remove any generics from higher level code
 * thus making it easier for the team to use listeners as all the potential ambiguity and confusion is removed.
 *
 * This implementation is for the event where the user presses or releases a key on their keyboard
 *
 */
public abstract class KeyboardInputListener extends InputListener<KeyEvent> {

    /**
     *
     * Default constructor passing the correct {@link Event.Type} that relate to this listener
     *
     */
    public KeyboardInputListener() {
        super(Event.Type.KEY_PRESSED,
                Event.Type.KEY_RELEASED);
    }
}
