package me.mayhem.input.impl.screen;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class GainedFocusListener extends InputListener<MouseEvent> {

    public GainedFocusListener() {
        super(Event.Type.GAINED_FOCUS);
    }
}
