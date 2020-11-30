package me.mayhem.input.test;

import me.mayhem.input.InputListener;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class KeyboardListener extends InputListener<KeyEvent> {

    public KeyboardListener() {
        super(Event.Type.KEY_PRESSED);
    }

    @Override
    protected void takeInput(KeyEvent event) {
        System.out.println("Test");
    }
}
