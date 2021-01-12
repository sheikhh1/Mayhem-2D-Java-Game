package me.mayhem;

import me.mayhem.game.GameManager;
import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

public class Mayhem {

    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 704;

    public static void main(String[] args) {
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(SCREEN_WIDTH, SCREEN_HEIGHT), "Mayhem", Window.CLOSE | Window.TITLEBAR);
        window.setFramerateLimit(30);

        GameManager gameManager = new GameManager(window);

        while (window.isOpen()) {
            window.clear(Color.WHITE);

            gameManager.draw();

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

            gameManager.tick();
        }
    }

    public static RenderWindow getMainWindow() {
        return null; //TODO
    }
}
