package me.mayhem.input.impl;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseInputListener extends InputListener<MouseEvent> {

    public MouseInputListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED,
                Event.Type.MOUSE_BUTTON_RELEASED,
                Event.Type.MOUSE_ENTERED,
                Event.Type.MOUSE_LEFT,
                Event.Type.MOUSE_MOVED,
                Event.Type.MOUSE_WHEEL_MOVED);
    }
}
