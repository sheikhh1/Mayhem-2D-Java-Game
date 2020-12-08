package me.mayhem.input;

import org.jsfml.window.event.Event;

import java.util.Objects;

public abstract class InputListener<T extends Event> {

    private final Event.Type type;

    public InputListener(Event.Type type) {
        this.type = type;
    }

    public Event.Type getType() {
        return this.type;
    }

    @SuppressWarnings("unchecked")
    public void onEvent(Event event) {
        if (!Objects.equals(this.type, event.type)) {
            return;
        }

        this.takeInput((T) event);
    }

    protected abstract void takeInput(T event);

}
