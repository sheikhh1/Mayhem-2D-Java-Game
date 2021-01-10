package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

public abstract class MouseButtonReleaseListener extends InputListener<MouseButtonEvent> {

    public MouseButtonReleaseListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED);
    }
}
