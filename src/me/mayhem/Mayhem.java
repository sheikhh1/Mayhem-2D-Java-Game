package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Mayhem {

    public static void main(String[] args) {
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), "Mayhem");

        window.setFramerateLimit(30);

        while (window.isOpen()) {
            window.clear(Color.WHITE);

            //TODO: tick drawables

            window.display();

            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                } else {
                    for (InputListener<?> listener : InputManager.getListeners(event.type)) {
                        listener.onEvent(event);
                    }
                }
            }

            //TODO: tick objects/entities
        }
    }
}
