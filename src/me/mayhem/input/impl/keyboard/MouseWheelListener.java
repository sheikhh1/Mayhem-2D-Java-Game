package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseWheelEvent;

public abstract class MouseWheelListener extends InputListener<MouseWheelEvent> {

    public MouseWheelListener() {
        super(Event.Type.MOUSE_WHEEL_MOVED);
    }
}
