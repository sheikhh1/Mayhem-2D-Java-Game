package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseWheelListener extends InputListener<MouseEvent> {

    public MouseWheelListener() {
        super(Event.Type.MOUSE_WHEEL_MOVED);
    }
}
