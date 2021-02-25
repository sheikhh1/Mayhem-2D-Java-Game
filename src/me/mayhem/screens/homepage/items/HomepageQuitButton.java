package me.mayhem.screens.homepage.items;

import me.mayhem.Mayhem;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class HomepageQuitButton extends ButtonInteractable {

    public HomepageQuitButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Quit");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            window.close();
        }
    }
}
