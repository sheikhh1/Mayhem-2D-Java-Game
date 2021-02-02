package me.mayhem.screens.escapescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ReturnToMainMenuButton extends ButtonInteractable {

    public ReturnToMainMenuButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Main Menu ");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        EscapeScreenManager screen = (EscapeScreenManager) Mayhem.getCurrentScreen();

        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            screen.unloadScreen(window);
            Mayhem.setCurrentScreen(new HomePageManager(window));
        }
    }
}
