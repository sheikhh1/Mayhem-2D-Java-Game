package me.mayhem.screens.homepage;

import me.mayhem.screens.ScreenManager;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

public class HomePageManager implements ScreenManager {
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        renderWindow.create(new VideoMode(640, 480), "Mayhem");
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        renderWindow.close();
    }
}
