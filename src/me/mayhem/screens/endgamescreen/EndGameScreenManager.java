package me.mayhem.screens.endgamescreen;

import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

import java.util.ArrayList;

public class EndGameScreenManager implements ScreenManager {
    private Interactable[] buttons;
    private Sprite[] sprites;
    private Sound mainTheme;


    @Override
    public void loadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interactable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {

    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Sound getSound() {
        return null;
    }
    private void createButtons(){

    }
    private void createSprites(){
        Sprite background = UtilSharedResources.getBackground();

        sprites = new Sprite[]{background};

    }
}
