package me.mayhem.screens.menu.load.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.home.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class LoadPageReturnButton extends ButtonInteractable {
    /**
     * a button that returns you to the main page
     *
     * @param shape the shape to create the buttton
     */
    public LoadPageReturnButton(Shape shape) {
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
