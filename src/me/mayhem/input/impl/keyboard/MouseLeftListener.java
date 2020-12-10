package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public abstract class MouseLeftListener extends InputListener<MouseEvent> {

    public MouseLeftListener() {
        super(Event.Type.MOUSE_LEFT);
    }
}
