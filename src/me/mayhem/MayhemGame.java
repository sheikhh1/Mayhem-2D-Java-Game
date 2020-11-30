package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import me.mayhem.input.test.KeyboardListener;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.util.List;

public class MayhemGame {

    private static final String GAME_NAME = "Mayhem";

    public static void main(String[] args) {
        //Create the window
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), GAME_NAME);

        //Limit the framerate
        window.setVerticalSyncEnabled(true);

        //Main loop
        while(window.isOpen()) {
            //Fill the window with red
            window.clear(Color.WHITE);

            //Display what was drawn (... the red color!)
            window.display();

            //Handle events
            for(Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    //The user pressed the close button
                    window.close();
                } else {
                   List<InputListener> listeners = InputManager.getListeners(event.type);

                    for (InputListener listener : listeners) {
                        listener.onEvent(event);
                    }
                }
            }
        }
    }

}
