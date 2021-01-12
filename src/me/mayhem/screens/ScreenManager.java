package me.mayhem.screens;

import org.jsfml.graphics.RenderWindow;

public interface ScreenManager {

    void loadScreen(RenderWindow renderWindow);

    void unloadScreen(RenderWindow renderWindow);

}