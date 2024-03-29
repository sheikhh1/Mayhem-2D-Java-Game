package me.mayhem.screens.end.lose.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.home.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button that returns you to the main menu
 */
public class LoseReturnButton extends ButtonInteractable {

    public LoseReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Main menu");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new HomePageManager(window));
    }
}
