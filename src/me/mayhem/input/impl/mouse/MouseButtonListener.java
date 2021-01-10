package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

public abstract class MouseButtonListener extends InputListener<MouseButtonEvent> {

    public MouseButtonListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED,
                Event.Type.MOUSE_BUTTON_RELEASED);
    }
}
