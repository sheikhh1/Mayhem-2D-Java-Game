package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseButtonListener extends InputListener<MouseEvent> {

    public MouseButtonListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED,
                Event.Type.MOUSE_BUTTON_RELEASED);
    }
}
