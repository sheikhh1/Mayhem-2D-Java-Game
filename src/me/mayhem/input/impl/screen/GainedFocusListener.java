package me.mayhem.input.impl.screen;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;

public abstract class GainedFocusListener extends InputListener<Event> {

    public GainedFocusListener() {
        super(Event.Type.GAINED_FOCUS);
    }
}
