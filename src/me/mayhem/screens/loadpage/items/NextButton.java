package me.mayhem.screens.loadpage.items;

import me.mayhem.Mayhem;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.loadpage.LoadPageManager;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.event.Event;

import java.awt.*;

public class NextButton extends ButtonInteractable{
    public NextButton(Shape shape) {
        super(shape, "fonts/FreeSans.ttf", "next");
    }

    @Override
    protected void call(RenderWindow window, Event event) {
        if ( event.type == Event.Type.MOUSE_BUTTON_PRESSED){
            LoadPageManager screen = (LoadPageManager) Mayhem.getCurrentScreen();
            if (screen.getSavepage() != screen.getMaxSavepages() ){
                screen.setSavepage(screen.getSavepage()  + 1);
                screen.setpagechange(true);
            }


        }


    }
}