package me.mayhem.screens.game.escape.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.game.escape.EscapeScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class ReturnToGameButton extends ButtonInteractable {
    /**
     * the button that allows you to return to the game
     *
     * @param shape the shape to create the button out of
     */
    public ReturnToGameButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Return to game ");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (!(Mayhem.getCurrentScreen() instanceof EscapeScreenManager)) {
            return;
        }

        EscapeScreenManager screen = (EscapeScreenManager) Mayhem.getCurrentScreen();

        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            screen.unloadScreen(window);
            screen.getGameScreen().loadScreen(Mayhem.getMainWindow());
            Mayhem.setCurrentScreen(screen.getGameScreen());
        }
    }
}
