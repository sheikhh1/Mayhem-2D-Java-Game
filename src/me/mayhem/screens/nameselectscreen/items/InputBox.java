package me.mayhem.screens.nameselectscreen.items;

import me.mayhem.util.Vector;
import me.mayhem.util.ui.impl.TextAreaInteractable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class InputBox extends TextAreaInteractable {

    public InputBox(Vector2f position) {
        super(new RectangleShape(position), "fonts/FreeSans.ttf");
    }
}
