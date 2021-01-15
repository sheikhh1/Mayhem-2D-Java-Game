package me.mayhem.game.event;

public @interface EventListener {

    EventPriority priority() default EventPriority.NORMAL;

    boolean ignoreCancelled() default true;

}
