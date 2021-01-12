package me.mayhem.util.ui;

import org.jsfml.graphics.RenderWindow;

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
     * Draws the stored button onto the target {@link RenderWindow}
     *
     * @param renderWindow The window being drawn onto
     */
    void draw(RenderWindow renderWindow);

}
