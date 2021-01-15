package me.mayhem.game.event.struct;

import java.util.function.Consumer;

public class EventHandler {

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
