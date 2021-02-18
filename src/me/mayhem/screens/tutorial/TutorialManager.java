package me.mayhem.screens.tutorial;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.tutorial.items.TutorialReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

import java.util.ArrayList;

public class TutorialManager implements ScreenManager {

    private final Sound mainTheme;

    private Interactable[] buttons;
    private Sprite[] sprites;

    public TutorialManager(RenderWindow window, Sound mainTheme){
        this.mainTheme = mainTheme;
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        createSprites();
        createButtons();
        draw(renderWindow);
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
        return this.mainTheme;
    }

    private void createButtons(){
        TutorialReturnButton returnButton = new TutorialReturnButton(createReturnButton());

        this.buttons = new Interactable[]{returnButton};

    }
    private Shape createReturnButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }
    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();
        Sprite WSAD = UtilSharedResources.getWSAD();
        Sprite keyCard = UtilSharedResources.getKeyCard();
        Sprite door = UtilSharedResources.getDoor();



    this.sprites = new Sprite[]{background, WSAD, keyCard, door};
    }

}
