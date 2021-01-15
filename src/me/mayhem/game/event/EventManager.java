package me.mayhem.game.event;

import java.util.Map;
import java.util.function.Consumer;

public class EventManager {

    private static Map<Class<? extends Event>, >

    public static void registerListener(Class<?> listenerClazz) {

    }

    private static final class EventListener {

        private final Consumer<Event> consumer;
        private final EventPriority priority;
        private final boolean ignoreCancelled;

        public EventListener(Consumer<Event> consumer, EventPriority priority, boolean ignoreCancelled) {
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
