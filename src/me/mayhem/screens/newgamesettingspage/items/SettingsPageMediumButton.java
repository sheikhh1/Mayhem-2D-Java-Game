package me.mayhem.screens.newgamesettingspage.items;

import me.mayhem.ui.AbstractInteractable;
import me.mayhem.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SettingsPageMediumButton extends ButtonInteractable {
    public SettingsPageMediumButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Medium");
    }

    @Override
    protected void call(RenderWindow window, Event event) {

    }
}
