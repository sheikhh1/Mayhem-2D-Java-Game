package me.mayhem.screens.menu.game.settings.items;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.menu.game.naming.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of {@link me.mayhem.util.ui.Interactable} and {@link ButtonInteractable} classes for the medium
 * button for the {@link me.mayhem.screens.menu.game.settings.NewGameSettingsPageManager} to set the difficulty to medium
 * for the next game
 *
 */
public class SettingsPageMediumButton extends ButtonInteractable {

    /**
     *
     * Default constructor taking the shape as the background
     *
     * @param shape Background shape
     */
    public SettingsPageMediumButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Medium");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new NameSelectScreen(window, Mayhem.getCurrentScreen().getSound(), Difficulty.MEDIUM));
    }
}
