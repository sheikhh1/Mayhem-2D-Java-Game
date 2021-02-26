package me.mayhem;

import me.mayhem.input.InputListener;
import me.mayhem.input.InputManager;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.home.HomePageManager;
import me.mayhem.util.UtilSharedResources;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Main class where the main JSFML logic is handled.
 * All events are polled and then passed off to the {@link InputManager} as not to pollute this class with any
 * methods that need to handle input.
 *
 * We also store any constants here such as the {@link Mayhem#SCREEN_HEIGHT} and {@link Mayhem#SCREEN_WIDTH}
 * We keep a static reference to the current screen and window for easy access from other classes
 *
 *
 */
public class Mayhem {

    /**
     *
     * Used for handling any off-main-thread activities such as loading resources
     *
     */
    public static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 800;

    private static ScreenManager currentScreen;
    private static RenderWindow mainWindow;

    /**
     *
     * The main function is where the game is run from
     *
     * @param args Unused in this instance - paramaters specified upon startup
     */
    public static void main(String[] args) {
        THREAD_POOL.submit(SaveFileManager::init);

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(SCREEN_WIDTH, SCREEN_HEIGHT), "Mayhem", Window.CLOSE | Window.TITLEBAR);
        window.setFramerateLimit(60);

        THREAD_POOL.submit(UtilSharedResources::init);

        currentScreen = new HomePageManager(window);
        mainWindow = window;

        while (window.isOpen()) {
            window.clear(Color.BLACK);

            if (currentScreen != null) {
                long startTime = System.currentTimeMillis();
                currentScreen.draw(window);
                long endTime = System.currentTimeMillis();
                System.out.println("Total time spend drawing: " + (endTime - startTime));
            }

            window.display();

            long startTime = System.currentTimeMillis();
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
            long endTime = System.currentTimeMillis();
            System.out.println("Total time spent on events: " + (endTime - startTime));
        }

        THREAD_POOL.shutdown();
    }

    /**
     *
     * Sets the window as active - used for when clicking on the game
     *
     * @param window The window being clicked
     * @param event The event causing the window to be going back into focus
     */
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
