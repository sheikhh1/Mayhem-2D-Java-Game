package me.mayhem.input;

import org.jsfml.window.event.Event;

import java.util.*;

public class InputManager {

    private static Map<Event.Type, List<InputListener>> registeredListeners = new HashMap<>();

    public static void registerInput(InputListener listener) {
        registeredListeners.computeIfAbsent(listener.getType(), ___ -> new ArrayList<>()).add(listener);
    }

    public static List<InputListener> getListeners(Event.Type type) {
        return registeredListeners.getOrDefault(type, Collections.emptyList());
    }

}
