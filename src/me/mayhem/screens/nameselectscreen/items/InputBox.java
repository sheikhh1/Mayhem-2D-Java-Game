package me.mayhem.screens.nameselectscreen.items;

import me.mayhem.util.ui.impl.TextAreaInteractable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class InputBox extends TextAreaInteractable {

    public InputBox() {
        super(new RectangleShape(new Vector2f(100, 100)), "fonts/FreeSans.ttf");
    }
}
