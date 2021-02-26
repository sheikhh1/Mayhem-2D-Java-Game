package me.mayhem.screens.menu.game.settings.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.menu.tutorial.TutorialManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button that lets you select the tutorial for the game
 */
public class SettingsPageTutorialButton extends ButtonInteractable {
    public SettingsPageTutorialButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "?");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if ( event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new TutorialManager(window, Mayhem.getCurrentScreen().getSound()));
        }
    }
}
