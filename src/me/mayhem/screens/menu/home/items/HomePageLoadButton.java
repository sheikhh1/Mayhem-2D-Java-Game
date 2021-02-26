package me.mayhem.screens.menu.home.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.game.load.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of {@link me.mayhem.util.ui.Interactable} and {@link ButtonInteractable} classes for the quit
 * button for the {@link me.mayhem.screens.menu.home.HomePageManager} to take the user to the {@link LoadPageManager}
 *
 */
public class HomePageLoadButton extends ButtonInteractable {

    /**
     * the button that lets you go the loading page
     *
     * @param shape the shape used to build the button
     */
    public HomePageLoadButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Load Game");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new LoadPageManager(window, Mayhem.getCurrentScreen().getSound()));
    }
}
