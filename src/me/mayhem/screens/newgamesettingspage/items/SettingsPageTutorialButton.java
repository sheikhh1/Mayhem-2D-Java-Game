package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.newgamesettingspage.NewGameSettingsPageManager;
import me.mayhem.screens.tutorial.TutorialManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageTutorialButton extends ButtonInteractable {
    public SettingsPageTutorialButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "How To Play");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if ( event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new TutorialManager(window, Mayhem.getCurrentScreen().getSound()));
        }
    }
}
