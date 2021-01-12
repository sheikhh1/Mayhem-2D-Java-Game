package me.mayhem.ui.impl;

import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;

public abstract class ButtonInteractable extends AbstractInteractable {

    private String[] text;

    public ButtonInteractable(Shape shape, String... text) {
        super(shape);

        this.text = text;
    }

    @Override
    public void draw(RenderWindow renderWindow) {

    }
}
