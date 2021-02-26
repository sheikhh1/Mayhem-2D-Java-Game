package me.mayhem.screens.endgamescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.endgamescreen.EndGameScreenManager;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a class to call a button that returns you back to the home screen
 */
public class ReturnButton extends ButtonInteractable {

    public ReturnButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Homepage ");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        EndGameScreenManager screen = (EndGameScreenManager) Mayhem.getCurrentScreen();

        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        screen.unloadScreen(window);
        Mayhem.setCurrentScreen(new HomePageManager(window));
    }
}