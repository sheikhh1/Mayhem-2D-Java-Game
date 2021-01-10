package me.mayhem.screens.homepage.items;

import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class HomePageNewGameButton extends AbstractInteractable {
    /**
     *
     * @param shape the shape of the button
     */
    public HomePageNewGameButton(Shape shape){
        super(shape);
    }

    /**
     * a call to create a new game, will move you to the game settings screen
     * @param window the current renderwindow
     * @param event the event that has occoured
     */
    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
