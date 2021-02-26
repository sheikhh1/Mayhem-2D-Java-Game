package me.mayhem.screens.winscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.save.SaveData;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.screens.winscreen.WinScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

/**
 * the button that lets you save the game when you finish the level
 */
public class WinSaveButton extends ButtonInteractable {
    public WinSaveButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Save and return to home");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        WinScreenManager screen = (WinScreenManager) Mayhem.getCurrentScreen();
        SaveData saveData = screen.getPreviousGame().getSaveData();

        saveData.setId(saveData.getId() + 1);

        SaveFileManager.save(saveData);

        screen.unloadScreen(window);
        Mayhem.setCurrentScreen(new HomePageManager(window));
    }
}
