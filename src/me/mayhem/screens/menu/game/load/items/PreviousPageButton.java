package me.mayhem.screens.menu.game.load.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.game.load.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button that allows you to navigate the save files
 */
public class PreviousPageButton extends ButtonInteractable {

    public PreviousPageButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "previous");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        LoadPageManager screen = (LoadPageManager) Mayhem.getCurrentScreen();

        if (screen.getPage() != 0) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new LoadPageManager(window, Mayhem.getCurrentScreen().getSound(), screen.getPage() - 1));
        }
    }
}
