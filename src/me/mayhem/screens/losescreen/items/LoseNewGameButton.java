package me.mayhem.screens.losescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.losescreen.LoseScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class LoseNewGameButton extends ButtonInteractable {

    public LoseNewGameButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Restart Level");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        LoseScreenManager screen = (LoseScreenManager) Mayhem.getCurrentScreen();
        screen.unloadScreen(window);
        Mayhem.setCurrentScreen(new GameScreenManager(window, screen.getDifficulty(), screen.getLevelId(), screen.getName()));
    }
}