package me.mayhem.screens.menu.game.naming.items;

import me.mayhem.Mayhem;
import me.mayhem.save.SaveData;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.game.game.GameScreenManager;
import me.mayhem.screens.menu.game.naming.NameSelectScreen;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class NameSelectContinueButton extends ButtonInteractable {

    public NameSelectContinueButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "confirm name");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        NameSelectScreen screen = (NameSelectScreen) Mayhem.getCurrentScreen();
        String data = screen.getName(screen.getInputBox());

        if (data.isEmpty() || SaveFileManager.doesNameExist(data)) {
            return;
        }

        screen.unloadScreen(window);
        SaveData saveData = this.createSaveFile(screen);
        Mayhem.setCurrentScreen(new GameScreenManager(window, saveData));
    }

    /**
     * creates the save data for the current run of the game
     *
     * @param screen
     * @return
     */
    private SaveData createSaveFile(NameSelectScreen screen) {
        SaveData data = SaveData.of(screen.getName(screen.getInputBox()), 1, screen.getDifficulty());

        SaveFileManager.addSaveFile(data);
        SaveFileManager.save(data);
        return data;
    }
}
