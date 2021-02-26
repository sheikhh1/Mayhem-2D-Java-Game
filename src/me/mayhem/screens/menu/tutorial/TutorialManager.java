package me.mayhem.screens.menu.tutorial;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.tutorial.items.TutorialReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

/**
 *
 * A tutorial screen manager that loads in the buttons and images for the screen
 *
 */
public class TutorialManager implements ScreenManager {

    private static final String[] TEXT = new String[]{
            "Welcome to Mayhem",
            "In Mayhem you must use the WASD keys to move your character",
            "Collect the KeyCard  and take it to the elevator to progress",
            "Can you make it out in time to save the world?"
    };

    private final Sound mainTheme;

    private final Interactable[] buttons = new Interactable[]{this.createReturnButton()};

    private Sprite[] sprites;
    private Text[] texts;

    public TutorialManager(RenderWindow window, Sound mainTheme){
        this.mainTheme = mainTheme;
        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createText();
        this.draw(renderWindow);
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
    public void close(RenderWindow renderWindow) {}

    @Override
    public Sound getSound() {
        return this.mainTheme;
    }

    private TutorialReturnButton createReturnButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new TutorialReturnButton(shape);
    }

    /**
     * creates the sprites that will appear on the screen
     */
    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();
        Sprite wasdKeys = UtilSharedResources.getWSAD();
        Sprite keyCard = UtilSharedResources.getKeyCard();
        Sprite door = UtilSharedResources.getDoor();

        wasdKeys.setPosition(new Vector(440, 40).toVector());
        wasdKeys.setScale(new Vector(0.5f, 0.5f).toVector());


        keyCard.setPosition(new Vector(260, 310).toVector());
        keyCard.setScale(new Vector(0.3f, 0.3f).toVector());

        door.setPosition(new Vector(800, 350).toVector());
        door.setScale(new Vector(0.5f, 0.5f).toVector());

        this.sprites = new Sprite[]{background, wasdKeys, keyCard, door};
    }

    /**
     * a method that creates the text objects that appear on the screen
     */
    private void createText() {
        this.texts = new Text[TEXT.length];

        for (int i = 0; i < TEXT.length; i++) {
            Text text = new Text(TEXT[i], UtilSharedResources.getMainFont());
            float width = text.getLocalBounds().width;

            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH / 2f) - (width / 2)) - 20, (Mayhem.SCREEN_HEIGHT / 6f) * i + 2).toVector());
            text.setScale(new Vector(1, 1).toVector());
            text.setStyle(TextStyle.BOLD);
            text.setColor(Color.CYAN);

            this.texts[i] = text;
        }
    }
}
