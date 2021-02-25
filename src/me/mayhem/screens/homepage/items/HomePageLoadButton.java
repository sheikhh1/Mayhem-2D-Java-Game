package me.mayhem.screens.homepage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

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
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new LoadPageManager(window, Mayhem.getCurrentScreen().getSound()));
        }
    }
}
