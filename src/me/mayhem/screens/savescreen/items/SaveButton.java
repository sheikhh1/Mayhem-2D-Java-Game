package me.mayhem.screens.savescreen.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.homepage.HomePageManager;
import me.mayhem.screens.savescreen.SaveScreenManager;
import me.mayhem.util.savegame.SaveManager;
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

        if (!(Mayhem.getCurrentScreen() instanceof SaveScreenManager)) {
            return;
        }

        SaveScreenManager screen = (SaveScreenManager) Mayhem.getCurrentScreen();

        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED){
            if (screen.getBox().getWritten() == null || screen.getBox().getWritten() == "" ){
                screen.getText().setColor(Color.RED);
            }
            else{
                System.out.println(("pressed"));
                SaveManager save = new SaveManager(screen, screen.getPrevScreen().getPrev());
                Mayhem.getCurrentScreen().unloadScreen(window);
                Mayhem.setCurrentScreen(new HomePageManager(window));
            }


        }
    }
}
