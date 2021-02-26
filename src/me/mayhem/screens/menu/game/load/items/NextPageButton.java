package me.mayhem.screens.menu.game.load.items;

import me.mayhem.Mayhem;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.menu.game.load.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * a button that allows you to navigate the savepage menu
 */
public class NextPageButton extends ButtonInteractable {

    public NextPageButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "next");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        LoadPageManager screen = (LoadPageManager) Mayhem.getCurrentScreen();
        int pages = (SaveFileManager.getSaveFiles().size()) / 3;

        if (screen.getPage() != pages) {
            if ((screen.getPage() + 3) == SaveFileManager.getSaveFiles().size()) {
                return;
            }

            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new LoadPageManager(window, Mayhem.getCurrentScreen().getSound(), screen.getPage() + 1));
        }
    }
}