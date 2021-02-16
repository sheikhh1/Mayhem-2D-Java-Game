package me.mayhem.input.impl.screen;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class LostFocusListener extends InputListener<MouseEvent> {

    public LostFocusListener() {
        super(Event.Type.LOST_FOCUS);
    }
}
