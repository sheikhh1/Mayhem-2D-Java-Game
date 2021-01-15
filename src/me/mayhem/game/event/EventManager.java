package me.mayhem.game.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class EventManager {

    private static final Map<Class<? extends Event>, Queue<EventHandler>> REGISTERED_LISTENERS = new HashMap<>();
    private static final Comparator<EventHandler> HANDLER_COMPARATOR = Comparator.comparingInt(o -> o.getPriority().ordinal());

    public static void registerListener(Object object) {
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            EventListener eventListener = declaredMethod.getAnnotation(EventListener.class);

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

            EventHandler eventHandler = new EventHandler(object.getClass(), event -> {
                try {
                    declaredMethod.invoke(object, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }, eventListener.priority(), eventListener.ignoreCancelled());

            REGISTERED_LISTENERS.computeIfAbsent(eventClass, ___ -> new PriorityQueue<>(HANDLER_COMPARATOR)).add(eventHandler);
        }
    }

    public static void unregisterListeners(Object object) {
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            EventListener eventListener = declaredMethod.getAnnotation(EventListener.class);

            if (eventListener == null) {
                continue;
            }

            declaredMethod.setAccessible(true);

            if (declaredMethod.getParameterTypes().length != 1) {
                continue;
            }

            if (!(Event.class.isAssignableFrom(declaredMethod.getParameterTypes()[0]))) {
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

    private static final class EventHandler {

        private final Class<?> parentClass;
        private final Consumer<Event> consumer;
        private final EventPriority priority;
        private final boolean ignoreCancelled;

        public EventHandler(Class<?> parentClass, Consumer<Event> consumer, EventPriority priority, boolean ignoreCancelled) {
            this.parentClass = parentClass;
            this.consumer = consumer;
            this.priority = priority;
            this.ignoreCancelled = ignoreCancelled;
        }

        public Class<?> getParentClass() {
            return this.parentClass;
        }

        public Consumer<Event> getConsumer() {
            return this.consumer;
        }

        public EventPriority getPriority() {
            return this.priority;
        }

        public boolean isIgnoreCancelled() {
            return this.ignoreCancelled;
        }
    }

}
