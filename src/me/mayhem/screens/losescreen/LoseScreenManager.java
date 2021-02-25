package me.mayhem.screens.losescreen;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
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
import org.jsfml.graphics.*;

public class LoseScreenManager implements ScreenManager {

    private static final String[] DEATH_TEXT = new String[]{
            "You have died",
            "Click retry to start the level again or ",
            "return to the main menu"
    };

    private final Sprite[] sprites = new Sprite[]{UtilSharedResources.getBackground()};
    private final Interactable[] buttons = new Interactable[]{
            this.createReturnButton(),
            this.createRetryButton()
    };

    private final Sound mainTheme;
    private final int levelId;
    private final String name;
    private final Difficulty difficulty;

    private Text[] texts;

    public LoseScreenManager(RenderWindow window, Sound mainTheme, GameScreenManager previous) {
        this.mainTheme = mainTheme;
        this.levelId = previous.getId();
        this.name = previous.getPlayerName();
        this.difficulty = previous.getDifficulty();

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createText();

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

        for (Text text : this.texts) {
            renderWindow.draw(text);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {}

    @Override
    public Sound getSound() {
        return this.mainTheme;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public String getName() {
        return this.name;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    private LoseReturnButton createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new LoseReturnButton(shape);
    }

    private LoseNewGameButton createRetryButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector(450, 350).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return new LoseNewGameButton(shape);
    }

    private void createText() {
        this.texts = new Text[DEATH_TEXT.length];

        for (int i = 0; i < DEATH_TEXT.length; i++) {
            Text text = new Text(DEATH_TEXT[i], UtilFont.loadFont("fonts/FreeSans.ttf"));
            float width = text.getLocalBounds().width;

            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH / 2f) - (width / 2)) - 20, (Mayhem.SCREEN_HEIGHT / 6f) + (50 * i)).toVector());
            text.setStyle(TextStyle.BOLD);
            text.setColor(Color.CYAN);

            this.texts[i] = text;
        }
    }
}