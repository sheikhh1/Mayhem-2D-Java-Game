package me.mayhem.ui;

/**
 *
 * An interface that flags a class as able to self-register and unregister a listener.
 * Said listener is then later used to check if the relevant clicks are related to the object
 * and as such call subsequent extended classes.
 *
 */
public interface Interatable {

    /**
     *
     * Registers the Interactable with input handlers
     *
     */
    void register();

    /**
     *
     * Unregisters the Interactable with input handlers
     *
     */
    void unregister();

}
