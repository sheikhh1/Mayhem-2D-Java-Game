package me.mayhem.input;

import org.jsfml.window.event.Event;

import java.util.Arrays;
import java.util.List;

public abstract class InputListener<T extends Event> {

    private final List<Event.Type> types;

    public InputListener(Event.Type... types) {
        this.types = Arrays.asList(types);

        InputManager.registerInput(this);
    }

    public List<Event.Type> getTypes() {
        return this.types;
    }

    @SuppressWarnings("unchecked")
    public void onEvent(Event event) {
        if (!this.types.contains(event.type)) {
            return;
        }

        this.takeInput((T) event);
    }

    protected abstract void takeInput(T event);

}
