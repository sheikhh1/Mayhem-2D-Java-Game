package me.mayhem.input.impl.keyboard;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public abstract class KeyboardPressListener extends InputListener<KeyEvent> {

    public KeyboardPressListener() {
        super(Event.Type.KEY_PRESSED);
    }
}
