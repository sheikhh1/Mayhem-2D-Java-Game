package me.mayhem.screens.nameselectscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.newgamesettingspage.NewGameSettingsPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button that can be used to return to the previous screen
 */
public class ReturnButton extends ButtonInteractable {
    public ReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new NewGameSettingsPageManager(window, Mayhem.getCurrentScreen().getSound()));
        }
    }
}
