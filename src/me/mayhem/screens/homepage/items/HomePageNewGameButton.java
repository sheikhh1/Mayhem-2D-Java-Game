package me.mayhem.screens.homepage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.newgamesettingspage.NewGameSettingsPageManager;
import me.mayhem.ui.AbstractInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

public class HomePageNewGameButton extends AbstractInteractable {

    /**
     *
     * @param shape the shape of the button
     */
    public HomePageNewGameButton(Shape shape){
        super(shape);
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            Mayhem.getCurrentScreen().unloadScreen(window);
            Mayhem.setCurrentScreen(new NewGameSettingsPageManager(window));
        }
    }
}
