package me.mayhem.game.event.struct;

/**
 *
 * Base class for all events
 *
 */
public abstract class Event {

    private boolean cancelled = false;

    /**
     *
     * Check if the event has been cancelled
     *
     * @return if the event has been cancelled this will be true
     */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     *
     * Cancel or uncancel the event
     *
     * @param cancelled Sets if the event has been cancelled
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
