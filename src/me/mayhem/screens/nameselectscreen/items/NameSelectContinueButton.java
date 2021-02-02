package me.mayhem.screens.nameselectscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.nameselectscreen.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class NameSelectContinueButton extends ButtonInteractable {
    public NameSelectContinueButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "confirm name");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            NameSelectScreen screen = (NameSelectScreen)  Mayhem.getCurrentScreen();
            screen.unloadScreen(window);
            Mayhem.setCurrentScreen(new GameScreenManager(window, screen.getDifficulty(), screen.getName(screen.getInputBox())));
        }
    }
}