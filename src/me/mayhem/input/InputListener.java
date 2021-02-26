package me.mayhem.input;

import org.jsfml.window.event.Event;

import java.util.Arrays;
import java.util.List;

/**
 *
 * InputListener is an abstract class used to redirect from the JSFML events handled in the
 * main loop in the {@link me.mayhem.Mayhem} class to the sub-classes for things such as buttons
 * and entities in the world
 *
 * InputListener automatically registers itself with the {@link InputManager} upon initialization.
 * This removes the dependency of {@link InputManager} from the other classes in the event of finding a
 * more efficient, simplistic, or elegant way of handling all the subsequent listeners
 *
 * InputListener can be extended directly for those confident. However, there are sub-classes for each of the
 * possible JSFML input events that can be extended to remove the complexity of the generics and {@link Event.Type}
 * combination created by JSFML.
 *
 */
public abstract class InputListener<T extends Event> {

    private final List<Event.Type> types;

    /**
     *
     * Default constructor taking varargs of all the types that it needs to listen to
     *
     * @param types The array of types this listener is waiting for
     */
    public InputListener(Event.Type... types) {
        this.types = Arrays.asList(types);

        this.register();
    }

    /**
     *
     * Registers the listener with the {@link InputManager} - called in the constructor
     *
     */
    public void register() {
        InputManager.registerInput(this);
    }

    /**
     *
     * Unregisters the listener with the {@link InputManager}
     *
     */
    public void unregister() {
        InputManager.unregisterInput(this);
    }

    public List<Event.Type> getTypes() {
        return this.types;
    }

    /**
     *
     * Called only by the {@link me.mayhem.Mayhem} class from the main loop
     * when the events are polled
     *
     * @param event The event being inputted
     */
    @SuppressWarnings("unchecked")
    public void onEvent(Event event) {
        if (!this.types.contains(event.type)) {
            return;
        }

        this.takeInput((T) event);
    }

    /**
     *
     * Sub-class protected, abstract method used to handle the specific event
     * once it has been determined this listener is correct for that type of event
     *
     * @param event The class specific event
     */
    protected abstract void takeInput(T event);

}
