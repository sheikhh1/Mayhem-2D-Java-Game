package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.menu.game.naming.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button to let you select medium as your difficulty
 */
public class SettingsPageMediumButton extends ButtonInteractable {
    public SettingsPageMediumButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Medium");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new NameSelectScreen(window, Mayhem.getCurrentScreen().getSound(), Difficulty.MEDIUM));
        }
    }
}
