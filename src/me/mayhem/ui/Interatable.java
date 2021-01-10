package me.mayhem.ui;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

public interface Interatable {

    void register();

    void unregister();

    void onInteract(RenderWindow window, Event event);

}
