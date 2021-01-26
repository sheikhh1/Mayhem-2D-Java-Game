package me.mayhem.screens.escapescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

import java.awt.*;

public class ReturnToGameButton extends ButtonInteractable {
    public ReturnToGameButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return to game ");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        EscapeScreenManager screen = (EscapeScreenManager) Mayhem.getCurrentScreen();
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            screen.unloadScreen(window);
            Mayhem.setCurrentScreen(screen.getPrevScreen());
        }
    }
}
