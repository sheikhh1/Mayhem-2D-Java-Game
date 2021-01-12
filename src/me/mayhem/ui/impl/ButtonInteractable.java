package me.mayhem.ui.impl;

import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Text;

public abstract class ButtonInteractable extends AbstractInteractable {

    private String[] text;

    public ButtonInteractable(Shape shape, Font font, String... text) {
        super(shape);

        this.text = text;
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        Text text = new Text("Test", );

        renderWindow.draw(super.shape);

    }
}
