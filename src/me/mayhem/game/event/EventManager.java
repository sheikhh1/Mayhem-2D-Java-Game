package me.mayhem.game.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventManager {

    private static final Map<Class<? extends Event>, List<EventListener>> REGISTERED_LISTENERS = new HashMap<>();

    public static void registerListener(Class<?> listenerClazz) {
        for (Method declaredMethod : listenerClazz.getDeclaredMethods()) {
            EventListener eventListener = declaredMethod.getAnnotation(EventListener.class);

        }

    }

    private static final class EventHandler {

        private final Consumer<Event> consumer;
        private final EventPriority priority;
        private final boolean ignoreCancelled;

        public EventHandler(Consumer<Event> consumer, EventPriority priority, boolean ignoreCancelled) {
            this.consumer = consumer;
            this.priority = priority;
            this.ignoreCancelled = ignoreCancelled;
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
