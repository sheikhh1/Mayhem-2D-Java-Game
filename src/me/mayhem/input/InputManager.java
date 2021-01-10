package me.mayhem.input;

import org.jsfml.window.event.Event;

import java.util.*;

/**
 *
 * The InputManager is a static factory that controls all the listeners in the project allowing the main loop in
 * the {@link me.mayhem.Mayhem} class to ping the correct classes with the correct events
 *
 */
public class InputManager {

    private static Map<Event.Type, List<InputListener<?>>> registeredListeners = new HashMap<>();

    /**
     *
     * Registers an {@link InputListener} to the {@link Event.Type}s specified by the listener
     *
     * @param listener The listener being registered
     */
    public static void registerInput(InputListener<?> listener) {
        for (Event.Type type : listener.getTypes()) {
            registeredListeners.computeIfAbsent(type, ___ -> new ArrayList<>()).add(listener);
        }
    }

    /**
     *
     * Unregisters an {@link InputListener} from all the {@link Event.Type}s specified by itself
     *
     * @param listener The listener being unregistered
     */
    public static void unregisterInput(InputListener<?> listener) {
        for (Event.Type type : listener.getTypes()) {
            registeredListeners.getOrDefault(type, new ArrayList<>()).remove(listener);
        }
    }

    /**
     *
     * Gets all the {@link InputListener}s registered to the specified {@link Event.Type}
     *
     * @param type The type being looked up
     * @return The {@link List} of listeners found that have been registered for that type
     */
    public static List<InputListener<?>> getListeners(Event.Type type) {
        return registeredListeners.getOrDefault(type, Collections.emptyList());
    }
}
