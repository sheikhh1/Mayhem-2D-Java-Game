package me.mayhem.screens.menu.home.items;

import me.mayhem.Mayhem;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of {@link me.mayhem.util.ui.Interactable} and {@link ButtonInteractable} classes for the quit
 * button for the {@link me.mayhem.screens.menu.home.HomePageManager} to quit the game
 *
 */
public class HomepageQuitButton extends ButtonInteractable {

    /**
     *
     * Constructor taking the background shape
     *
     * @param shape the background shape
     */
    public HomepageQuitButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Quit");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        window.close();
    }
}
