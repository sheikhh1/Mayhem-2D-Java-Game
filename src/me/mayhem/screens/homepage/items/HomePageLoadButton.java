package me.mayhem.screens.homepage.items;

import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class HomePageLoadButton extends AbstractInteractable {
    public HomePageLoadButton(Shape shape) {
        super(shape);
    }

    /**
     * calls a load game page manager
     * @param window the current renderwindow
     * @param event the event that has occoured
     */
    @Override
    protected void call(RenderWindow window, Event event) {
    }
}
