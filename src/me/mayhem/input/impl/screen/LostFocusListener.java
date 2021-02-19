package me.mayhem.input.impl.screen;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;

public abstract class LostFocusListener extends InputListener<Event> {

    public LostFocusListener() {
        super(Event.Type.LOST_FOCUS);
    }
}
