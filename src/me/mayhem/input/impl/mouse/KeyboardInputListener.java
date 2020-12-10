package me.mayhem.input.impl.mouse;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class KeyboardInputListener extends InputListener<KeyEvent> {

    public KeyboardInputListener() {
        super(Event.Type.KEY_PRESSED,
                Event.Type.KEY_RELEASED);
    }
}
