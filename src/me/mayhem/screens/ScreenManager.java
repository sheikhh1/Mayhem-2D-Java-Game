package me.mayhem.screens;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;

/**
 *
 * Screen manager interface for handling the different screens the game passes through
 *
 */
public interface ScreenManager {

    /**
     * loads items onto a render window
     *
     * @param renderWindow is the renderwindow that is to be loaded onto
     */
    void loadScreen(RenderWindow renderWindow);

    /**
     * unloads items from a renderwindow
     *
     * @param renderWindow is the renderwindow that is to be unloaded
     */
    void unloadScreen(RenderWindow renderWindow);

    /**
     * Draws items onto a given renderwindow
     *
     * @param renderWindow is the renderwindow to draw items onto
     */
    void draw(RenderWindow renderWindow);

    /**
     * closes the given renderwindow so that it is not used anymore
     *
     * @param renderWindow the renderwindow that is to be closed
     */
    void close(RenderWindow renderWindow);

    /**
     * gets the sound varaible for each screen manager instance
     *
     * @return the sound variable containing the main theme
     */
    Sound getSound();

}
