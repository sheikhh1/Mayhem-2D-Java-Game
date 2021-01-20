package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageReturnButton extends ButtonInteractable {
    public SettingsPageReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if ( event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new HomePageManager(window, Mayhem.getCurrentScreen().getSound()));
        }
    }
}
