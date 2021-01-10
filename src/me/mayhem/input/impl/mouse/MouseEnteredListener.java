package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseEnteredListener extends InputListener<MouseEvent> {

    public MouseEnteredListener() {
        super(Event.Type.MOUSE_ENTERED);
    }
}
