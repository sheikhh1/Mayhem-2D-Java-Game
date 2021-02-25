package me.mayhem.screens.endgamescreen;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.endgamescreen.items.ReturnButton;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.loadpage.items.LoadPageReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

import java.util.ArrayList;

public class EndGameScreenManager implements ScreenManager {
    private Interactable[] buttons;
    private Sprite[] sprites;
    private Text[] texts;

    private Sound mainTheme;
    private GameScreenManager prev;

    public EndGameScreenManager(RenderWindow window, Sound mainTheme, GameScreenManager prev){
        this.mainTheme = mainTheme;
        this.prev = prev;
        loadScreen(window);


    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {

        createButtons();
        createSprites();
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
        for (Text text: texts){
            renderWindow.draw(text);
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
        ReturnButton returnButton = createReturnButton();

        buttons = new Interactable[]{returnButton};

    }
    private void createSprites(){
        Sprite background = UtilSharedResources.getBackground();

        sprites = new Sprite[]{background};

    }

    private ReturnButton createReturnButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new ReturnButton(shape);
    }
    private void createText(){
        String[] writing = initialiseText();

        this.texts = new Text[writing.length];

        for (int i = 0; i <= writing.length - 1; i++){

            Text text = new Text(writing[i], UtilSharedResources.getMainFont());
            Float width = text.getLocalBounds().width;
            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH/ 2f) - (width/2)) - 20, (Mayhem.SCREEN_HEIGHT/6f ) * i + 2 ).toVector());
            text.setScale(new Vector(1,1).toVector());
            text.setStyle(TextStyle.BOLD);
            text.setColor(Color.CYAN);

            this.texts[i] = text;

        }
    }

    private String[] initialiseText(){
        String line1 = "Congratulations";
        String line2 = "You have beaten the game with a time of ";
        String line3 = this.prev.getGame().getTimerText();


        return new String[]{line1,line2,line3};

    }
    public GameScreenManager getGameScreen(){
        return prev;
    }
}
