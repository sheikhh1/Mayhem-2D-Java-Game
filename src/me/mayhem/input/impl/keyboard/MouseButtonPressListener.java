package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

public abstract class MouseButtonPressListener extends InputListener<MouseButtonEvent> {

    public MouseButtonPressListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED);
    }
}
