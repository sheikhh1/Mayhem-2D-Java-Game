package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.ui.AbstractInteractable;
import me.mayhem.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageHardButton extends ButtonInteractable {
    public SettingsPageHardButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Hard");
    }

    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
