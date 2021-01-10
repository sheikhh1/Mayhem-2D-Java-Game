package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.HomePageManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Mayhem {

    private static ScreenManager currentScreen;

    public static void main(String[] args) {
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), "Mayhem");

        currentScreen = new HomePageManager(window);

        window.setFramerateLimit(30);

        while (window.isOpen()) {
            window.clear(Color.GREEN);


            //TODO: tick drawables
            if (currentScreen != null) {
                currentScreen.draw(window);
            }

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

    public static RenderWindow getMainWindow() {
        return null; //TODO
    }

    public static ScreenManager getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(ScreenManager currentScreen) {
        Mayhem.currentScreen = currentScreen;
    }
}
