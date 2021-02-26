package me.mayhem.screens.menu.home.items;


import me.mayhem.Mayhem;
import me.mayhem.screens.menu.game.settings.NewGameSettingsPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 *
 * Concrete implementation of {@link me.mayhem.util.ui.Interactable} and {@link ButtonInteractable} classes for the quit
 * button for the {@link me.mayhem.screens.menu.home.HomePageManager} to start a new game
 *
 */
public class HomePageNewGameButton extends ButtonInteractable {

    /**
     * the button to let you start a new game
     *
     * @param shape the shape of the button
     */
    public HomePageNewGameButton(Shape shape){
        super(shape, "fonts/FreeSans.ttf", "New Game");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new NewGameSettingsPageManager(window, Mayhem.getCurrentScreen().getSound()));
    }
}
