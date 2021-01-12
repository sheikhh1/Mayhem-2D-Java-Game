package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageEasyButton extends ButtonInteractable {
    public SettingsPageEasyButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Easy");
    }

    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
