package me.mayhem.screens.winscreen.items;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.Entity;
import me.mayhem.screens.savescreen.SaveScreenManager;
import me.mayhem.screens.winscreen.WinScreenManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class WinSaveButton extends ButtonInteractable {
    public WinSaveButton(Shape shape) {super(shape, "fonts/FreeSans.ttf", "Return"); }
    @Override
    protected void call(RenderWindow window, Event event) {
        if ( event.type == Event.Type.MOUSE_BUTTON_PRESSED){

            WinScreenManager screen = (WinScreenManager) Mayhem.getCurrentScreen();
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new SaveScreenManager(window,Mayhem.getCurrentScreen().getSound(),screen));
        }
    }
}
