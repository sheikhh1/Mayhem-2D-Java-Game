package me.mayhem.game.event;

import me.mayhem.game.event.struct.Event;
import me.mayhem.game.event.struct.EventHandler;
import me.mayhem.game.event.struct.EventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventManager {

    private static final Map<Class<? extends Event>, Queue<EventHandler>> REGISTERED_LISTENERS = new HashMap<>();
    private static final Comparator<EventHandler> HANDLER_COMPARATOR = Comparator.comparingInt(o -> o.getPriority().ordinal());

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
                    .consumer(event -> invokeEvent(event, object, declaredMethod))
                    .eventListener(eventListener)
                    .build();

            REGISTERED_LISTENERS.computeIfAbsent(eventClass, ___ -> new PriorityQueue<>(HANDLER_COMPARATOR)).add(eventHandler);
        }
    }

    private static void invokeEvent(Event event, Object object, Method method) {
        try {
            method.invoke(object, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

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

    public static void callEvent(Event event) {
        for (EventHandler eventHandler : REGISTERED_LISTENERS.get(event.getClass())) {
            eventHandler.getConsumer().accept(event);
        }
    }
}
