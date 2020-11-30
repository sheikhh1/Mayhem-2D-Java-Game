package me.mayhem.input;

import org.jsfml.window.event.Event;

public interface InputHandler<T extends Event> {

    void takeInput(T event);

}
