package me.mayhem.screens.menu.game.naming.items;

import me.mayhem.util.ui.impl.TextAreaInteractable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 *
 * The input box for the user to write into
 *
 */
public class InputBox extends TextAreaInteractable {

    /**
     * a box that can be used to input text into the game
     *
     * @param position the position of the input box
     */
    public InputBox(Vector2f position) {
        super(new RectangleShape(position), "fonts/FreeSans.ttf");
    }
}
