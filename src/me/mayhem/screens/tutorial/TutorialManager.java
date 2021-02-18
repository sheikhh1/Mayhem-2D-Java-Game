package me.mayhem.screens.tutorial;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.newgamesettingspage.NewGameSettingsPageManager;
import me.mayhem.screens.tutorial.items.TutorialReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.ui.Interactable;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.ArrayList;

public class TutorialManager implements ScreenManager {

    private final Sound mainTheme;

    private Interactable[] buttons;
    private Sprite[] sprites;
    private Text[] texts;

    public TutorialManager(RenderWindow window, Sound mainTheme){
        this.mainTheme = mainTheme;
        loadScreen(window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        createSprites();
        createButtons();
        createText();
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

        for (Sprite sprite : sprites){
            renderWindow.draw(sprite);
        }

        for (Interactable button: buttons){
            button.draw(renderWindow);
        }
        for (Text words: this.texts){
            renderWindow.draw(words);
        }
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

        WSAD.setPosition(new Vector(200, 400).toVector());
        WSAD.setScale(new Vector(0.5f,0.5f).toVector());


        keyCard.setPosition(new Vector(400, 400).toVector());
        keyCard.setScale(new Vector(0.3f,0.3f).toVector());

        door.setPosition(new Vector(600, 600).toVector());
        door.setScale(new Vector(0.5f,0.5f).toVector());

    this.sprites = new Sprite[]{background, WSAD, keyCard, door};
    }

    private void createText(){
        String[] writing = initialiseText();

        this.texts = new Text[writing.length];

        for (int i = 0; i <= writing.length - 1; i++){

            Text text = new Text(writing[i], UtilFont.loadFont("fonts/FreeSans.ttf"));
            Float width = text.getLocalBounds().width;
            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH/ 2) - (width/2)), (Mayhem.SCREEN_HEIGHT/6 ) * i + 2 ).toVector());
            text.setScale(new Vector(1,1).toVector());

            this.texts[i] = text;

        }
    }

    private String[] initialiseText(){
        String line1 = "Welcome to Mayhem";
        String line2 = "In mayhem you must use the WSAD keys to move your character";
        String line3 = "Collect the KeyCard  and take it to the elevator to progress";
        String line4 = "Can you make it out in time to save the world?";

        return new String[]{line1,line2,line3, line4};

    }

}
