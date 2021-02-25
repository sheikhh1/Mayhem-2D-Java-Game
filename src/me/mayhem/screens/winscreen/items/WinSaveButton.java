package me.mayhem.screens.winscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.winscreen.WinScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class WinSaveButton extends ButtonInteractable {
    public WinSaveButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Save and return to home");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        WinScreenManager screen = (WinScreenManager) Mayhem.getCurrentScreen();
        //TODO: save
    }
}
