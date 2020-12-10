package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseMovementListener extends InputListener<MouseEvent> {

    public MouseMovementListener() {
        super(Event.Type.MOUSE_BUTTON_PRESSED);
    }
}
