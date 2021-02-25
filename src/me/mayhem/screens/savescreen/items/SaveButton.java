package me.mayhem.screens.savescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.screens.savescreen.SaveScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class SaveButton extends ButtonInteractable {

    public SaveButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "Save game");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type != Event.Type.MOUSE_BUTTON_PRESSED) {
            return;
        }

        SaveScreenManager screen = (SaveScreenManager) Mayhem.getCurrentScreen();

        if (screen.getBox().getWritten() == null || screen.getBox().getWritten().equals("")) {
            screen.getText().setColor(Color.RED);
        } else {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new HomePageManager(window));
        }
    }
}
