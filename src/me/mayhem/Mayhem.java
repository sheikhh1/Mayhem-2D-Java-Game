package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.util.file.UtilFont;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

public class Mayhem {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 800;

    private static ScreenManager currentScreen;
    private static RenderWindow mainWindow;

    public static void main(String[] args) {
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(SCREEN_WIDTH, SCREEN_HEIGHT), "Mayhem", Window.CLOSE | Window.TITLEBAR);
        window.setVerticalSyncEnabled(true);

        currentScreen = new HomePageManager(window);
        mainWindow = window;

        while (window.isOpen()) {
            window.clear(Color.BLACK);

            if (currentScreen != null) {
                currentScreen.draw(window);
            }

            window.display();

            for (Event event : window.pollEvents()) {
                setActive(window, event);

                if (event.type == Event.Type.CLOSED) {
                    window.close();
                } else {
                    for (InputListener<?> listener : InputManager.getListeners(event.type)) {
                        listener.onEvent(event);
                    }
                }
            }

        }
    }

    private static void setActive(RenderWindow window, Event event) {
        if (event instanceof MouseEvent) {
            try {
                window.setActive(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static RenderWindow getMainWindow() {
        return mainWindow;
    }

    public static ScreenManager getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(ScreenManager currentScreen) {
        Mayhem.currentScreen = currentScreen;
    }
}
