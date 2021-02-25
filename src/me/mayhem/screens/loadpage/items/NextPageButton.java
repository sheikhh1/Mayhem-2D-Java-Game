package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

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

        if (screen.getPage() != (SaveFileManager.getSaveFiles().size()) / 3) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new LoadPageManager(window, Mayhem.getCurrentScreen().getSound(), screen.getPage() + 1));
        }
    }
}