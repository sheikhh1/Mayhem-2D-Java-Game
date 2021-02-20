package me.mayhem.screens.winscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
import me.mayhem.screens.savescreen.SaveScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class WinContinueButton extends ButtonInteractable {
    public WinContinueButton(Shape shape) {super(shape, "fonts/FreeSans.ttf", "continue");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if (!(Mayhem.getCurrentScreen() instanceof EscapeScreenManager)) {
            return;
        }

        EscapeScreenManager screen = (EscapeScreenManager) Mayhem.getCurrentScreen();

        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {

            screen.unloadScreen(window);
            Mayhem.setCurrentScreen(new SaveScreenManager(window,screen.getSound(),screen));
        }
    }
}
