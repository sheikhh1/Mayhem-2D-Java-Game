package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class KeyboardReleaseListener extends InputListener<KeyEvent> {

    public KeyboardReleaseListener() {
        super(Event.Type.KEY_RELEASED);
    }
}
