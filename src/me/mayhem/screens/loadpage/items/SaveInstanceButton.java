package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.util.loadgame.LoadManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

import java.io.File;


public class SaveInstanceButton extends ButtonInteractable {
    private File myfile;

    public SaveInstanceButton(Shape shape, File file) {

        super(shape, "fonts/FreeSans.ttf", "filebutton");
        this.myfile = file;
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            System.out.println(getFilename());
            LoadManager loader = new LoadManager(getFilename());
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new GameScreenManager(window, Difficulty.EASY, loader.getCharName()));

        }
    }

    public String getFilename() {
        return myfile.getName().substring(0, myfile.getName().length() - 4);
    }
}
