package me.mayhem;

import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardPressListener;
import me.mayhem.game.entity.player.listeners.PlayerKeyboardReleaseListener;
import me.mayhem.game.entity.player.listeners.PlayerMousePressListener;
import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import me.mayhem.math.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Mayhem {

    public static void main(String[] args) {
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), "Mayhem");
        Player myPlayer = new Player("test", new Vector(100,100));
        window.setFramerateLimit(30);

        new PlayerMousePressListener();
        new PlayerKeyboardPressListener(myPlayer);
        new PlayerKeyboardReleaseListener(myPlayer);

        while (window.isOpen()) {
            window.clear(Color.WHITE);

            //TODO: tick drawables
            myPlayer.update(window); // Draw Player onto window
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
