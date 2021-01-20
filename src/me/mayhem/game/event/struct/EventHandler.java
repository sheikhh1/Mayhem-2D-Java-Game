package me.mayhem.game.event.struct;

import java.util.function.Consumer;

public class EventHandler {

    private final Class<?> parentClass;
    private final Consumer<Event> consumer;
    private final EventPriority priority;
    private final boolean ignoreCancelled;

    private EventHandler(Class<?> parentClass, Consumer<Event> consumer, EventPriority priority, boolean ignoreCancelled) {
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Class<?> parentClass;
        private Consumer<Event> consumer;
        private EventPriority eventPriority;
        private boolean ignoreCancelled;

        protected Builder() {}

        public Builder parentClass(Class<?> parentClass) {
            this.parentClass = parentClass;
            return this;
        }

        public Builder consumer(Consumer<Event> consumer) {
            this.consumer = consumer;
            return this;
        }

        public Builder eventListener(EventListener eventListener) {
            this.eventPriority = eventListener.priority();
            this.ignoreCancelled = eventListener.ignoreCancelled();
            return this;
        }

        public EventHandler build() {
            return new EventHandler(this.parentClass, this.consumer, this.eventPriority, this.ignoreCancelled);
        }
    }
}
