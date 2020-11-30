package me.mayhem.entity;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public interface Entity {

    int getId();

    int getPriority();

    Vector2f getPosition();

    void setPosition(Vector2f position);

    void draw(RenderWindow window);

}
