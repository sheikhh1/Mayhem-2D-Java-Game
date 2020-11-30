package me.mayhem.entity;

import org.jsfml.system.Vector2f;

public interface Entity {

    int getId();

    Vector2f getPosition();

    void setPosition(Vector2f position);

}
