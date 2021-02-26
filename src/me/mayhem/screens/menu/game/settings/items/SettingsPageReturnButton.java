package me.mayhem.screens.menu.game.settings.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.home.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of {@link me.mayhem.util.ui.Interactable} and {@link ButtonInteractable} classes for the return
 * button for the {@link me.mayhem.screens.menu.game.settings.NewGameSettingsPageManager} to take the user to the {@link HomePageManager}
 *
 */
public class SettingsPageReturnButton extends ButtonInteractable {

    /**
     *
     * Default constructor taking the shape as the background
     *
     * @param shape Background shape
     */
    public SettingsPageReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new HomePageManager(window, Mayhem.getCurrentScreen().getSound()));
    }
}
