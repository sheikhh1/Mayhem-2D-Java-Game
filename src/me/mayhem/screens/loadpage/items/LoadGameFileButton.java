package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.save.SaveData;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;


public class LoadGameFileButton extends ButtonInteractable {

    private final SaveData loadFile;

    public LoadGameFileButton(Shape shape, SaveData loadFile) {
        super(shape, "fonts/FreeSans.ttf", loadFile.getName());

        this.loadFile = loadFile;
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        Mayhem.getCurrentScreen().unloadScreen(window);
        Mayhem.setCurrentScreen(new GameScreenManager(window, this.loadFile.getDifficulty(), this.loadFile.getId(), this.loadFile.getName()));
    }
}
