package me.mayhem.screens.end.win.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.end.win.WinScreenManager;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * the button that lets you continue to the next level when you win
 */
public class WinContinueButton extends ButtonInteractable {

    public WinContinueButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "continue");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        WinScreenManager screen = (WinScreenManager) Mayhem.getCurrentScreen();
        screen.getPreviousGame().getSaveData().setId(screen.getPreviousGame().getSaveData().getId() + 1);
        screen.unloadScreen(window);
        Mayhem.setCurrentScreen(new GameScreenManager(window, screen.getPreviousGame().getSaveData()));
    }
}
