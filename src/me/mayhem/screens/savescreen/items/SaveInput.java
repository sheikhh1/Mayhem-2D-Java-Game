package me.mayhem.screens.savescreen.items;

import me.mayhem.util.ui.impl.TextAreaInteractable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class SaveInput extends TextAreaInteractable {

    public SaveInput(Vector2f position) {
        super(new RectangleShape(position), "fonts/FreeSans.ttf");
    }

}
