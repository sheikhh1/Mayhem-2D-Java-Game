package me.mayhem.screens.losescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.screens.losescreen.LoseScreenManager;
import me.mayhem.screens.nameselectscreen.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class LoseReturnButton extends ButtonInteractable {
    public LoseReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Main menu");
    }
    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            LoseScreenManager screen = (LoseScreenManager)  Mayhem.getCurrentScreen();
            screen.unloadScreen(window);
            Mayhem.setCurrentScreen(new HomePageManager(window));
        }
    }
}