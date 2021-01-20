package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.screens.nameselectscreen.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageEasyButton extends ButtonInteractable {
    public SettingsPageEasyButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Easy");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new NameSelectScreen(window, Mayhem.getCurrentScreen().getSound(), Difficulty.EASY));
        }
    }
}
