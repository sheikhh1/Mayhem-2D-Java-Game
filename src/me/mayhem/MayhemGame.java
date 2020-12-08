package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MayhemGame {

    private static final String GAME_NAME = "Mayhem";

    public static void main(String[] args) {
        //Create the window
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), GAME_NAME);

        Font font = new Font();

        try {
            font.loadFromFile(Paths.get("libs/FreeMono.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text text = new Text("This is an example text.", font, 24);
        text.setPosition(new Vector2f(100, 100));
        text.setColor(Color.RED);

        //Limit the framerate
        window.setVerticalSyncEnabled(true);

        //Main loop
        while(window.isOpen()) {
            //Fill the window with red
            window.clear(Color.WHITE);

            window.draw(text);

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
