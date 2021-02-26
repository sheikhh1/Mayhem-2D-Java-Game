package me.mayhem.screens.game.escape.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.game.escape.EscapeScreenManager;
import me.mayhem.screens.menu.home.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ReturnToMainMenuButton extends ButtonInteractable {
    /**
     * a button that allows you to go back to the main menu
     *
     * @param shape the shape that you create the button out of
     */
    public ReturnToMainMenuButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Main Menu ");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        EscapeScreenManager screen = (EscapeScreenManager) Mayhem.getCurrentScreen();

        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            screen.unloadScreen(window);
            screen.getGameScreen().getGame().shutdownLevel();
            Mayhem.setCurrentScreen(new HomePageManager(window));
        }
    }
}
