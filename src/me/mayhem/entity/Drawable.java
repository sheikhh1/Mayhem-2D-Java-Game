package me.mayhem.entity;

import org.jsfml.graphics.RenderWindow;

public interface Drawable {

    int getPriority();

    void draw(RenderWindow window);

}
