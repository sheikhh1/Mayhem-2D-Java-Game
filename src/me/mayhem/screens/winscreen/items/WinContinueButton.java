package me.mayhem.screens.winscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.losescreen.LoseScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class WinContinueButton extends ButtonInteractable {

    public WinContinueButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "continue");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        LoseScreenManager screen = (LoseScreenManager) Mayhem.getCurrentScreen();
        screen.getSaveData().setId(screen.getSaveData().getId() + 1);
        screen.unloadScreen(window);
        Mayhem.setCurrentScreen(new GameScreenManager(window, screen.getSaveData()));
    }
}
