package me.mayhem.game.event;

import me.mayhem.game.event.struct.Event;
import me.mayhem.game.event.struct.EventHandler;
import me.mayhem.game.event.struct.EventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * EventManager static factory for registering, unregistering and calling game events.
 *
 */
public class EventManager {

    private static final Map<Class<? extends Event>, Queue<EventHandler>> REGISTERED_LISTENERS = new HashMap<>();
    private static final Comparator<EventHandler> HANDLER_COMPARATOR = Comparator.comparingInt(o -> o.getPriority().ordinal());

    /**
     *
     * Iterates through all declared methods in the class and finds those annotated with {@link EventListener} and
     * caches them to be called when the Event occurs.
     *
     * @param object The class with {@link EventListener} annotated methods to be registered
     */
    public static void registerListener(Object object) {
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            EventListener eventListener = declaredMethod.getAnnotation(me.mayhem.game.event.struct.EventListener.class);

            if (eventListener == null) {
                continue;
            }

            declaredMethod.setAccessible(true);

            if (declaredMethod.getParameterTypes().length != 1) {
                throw new IllegalArgumentException("EventListener can only have one parameter");
            }

            if (!(Event.class.isAssignableFrom(declaredMethod.getParameterTypes()[0]))) {
                throw new IllegalArgumentException("First parameter of EventListener must extend event");
            }

            Class<? extends Event> eventClass = (Class<? extends Event>) declaredMethod.getParameterTypes()[0];
            EventHandler eventHandler = EventHandler.builder()
                    .parentClass(object.getClass())
                    .consumer(event -> invokeEvent(event, object, declaredMethod, eventListener))
                    .eventListener(eventListener)
                    .build();

            REGISTERED_LISTENERS.computeIfAbsent(eventClass, ___ -> new PriorityQueue<>(HANDLER_COMPARATOR)).add(eventHandler);
        }
    }

    /**
     *
     * Invokes the method from the class witht he event object
     *
     * @param event The event occurring
     * @param object The object which the method belongs to
     * @param method The method being called when the event occurs
     */
    private static void invokeEvent(Event event, Object object, Method method, EventListener listener) {
        if (event.isCancelled() && listener.ignoreCancelled()) {
            return;
        }

        try {
            method.invoke(object, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Iterates through all declared methods in the class and finds those annotated with {@link EventListener} and
     * unregisters all the methods.
     *
     * @param object The class with {@link EventListener} annotated methods to be unregistered
     */
    public static void unregisterListeners(Object object) {
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            EventListener eventListener = declaredMethod.getAnnotation(EventListener.class);

            if (eventListener == null) {
                continue;
            }

            declaredMethod.setAccessible(true);

            if (declaredMethod.getParameterTypes().length != 1 || !(Event.class.isAssignableFrom(declaredMethod.getParameterTypes()[0]))) {
                continue;
            }

            Class<? extends Event> eventClass = (Class<? extends Event>) declaredMethod.getParameterTypes()[0];

            REGISTERED_LISTENERS.getOrDefault(eventClass, new PriorityQueue<>(HANDLER_COMPARATOR))
                    .removeIf(handler -> handler.getParentClass() == object.getClass());
        }
    }

    /**
     *
     * Calls the event to all registered event listeners
     *
     * @param event The event being called
     */
    public static void callEvent(Event event) {
        for (EventHandler eventHandler : REGISTERED_LISTENERS.getOrDefault(event.getClass(), new ArrayDeque<>())) {
            eventHandler.getConsumer().accept(event);
        }
    }
}
