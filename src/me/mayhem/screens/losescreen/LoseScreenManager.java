package me.mayhem.screens.losescreen;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.losescreen.items.LoseNewGameButton;
import me.mayhem.screens.losescreen.items.LoseReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;

import javax.annotation.processing.RoundEnvironment;

public class LoseScreenManager implements ScreenManager {
    private Interactable[] buttons;
    private Sprite[] sprites;
    private Text[] texts;

    private Sound mainTheme;


    public LoseScreenManager(RenderWindow window, Sound mainTheme, GameScreenManager prev){
        this.mainTheme = mainTheme;

        loadScreen(window);

    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        createButtons();
        createSprites();

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
        for (Sprite sprite : this.sprites) {
            renderWindow.draw(sprite);
        }

        for (Interactable button : this.buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Sound getSound() {
        return null;
    }

    private void createButtons(){
        LoseReturnButton returnButton = new LoseReturnButton(createReturnButton());
        LoseNewGameButton newGameButton = new LoseNewGameButton(createRetryButton());

        buttons = new Interactable[]{returnButton,newGameButton};
    }

    private Shape createReturnButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;

    }
    private Shape createRetryButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector(450, 350).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }


    private void createSprites(){
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createText(){
        String[] writing = initialiseText();

        this.texts = new Text[writing.length];

        for (int i = 0; i <= writing.length - 1; i++){

            Text text = new Text(writing[i], UtilFont.loadFont("fonts/FreeSans.ttf"));
            Float width = text.getLocalBounds().width;
            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH/ 2) - (width/2)) - 20, (Mayhem.SCREEN_HEIGHT/6 ) * i + 2 ).toVector());
            text.setScale(new Vector(1,1).toVector());
            text.setStyle(TextStyle.BOLD);
            text.setColor(Color.CYAN);

            this.texts[i] = text;

        }
    }
    private String[] initialiseText(){
        String line1 = "You have died";
        String line2 = "Click retry to start the level agian or ";
        String line3 = "return to the main menu";


        return new String[]{line1,line2,line3};

    }


}