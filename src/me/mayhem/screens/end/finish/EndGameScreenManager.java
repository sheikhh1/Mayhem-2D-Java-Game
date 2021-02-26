package me.mayhem.screens.end.finish;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.end.finish.items.ReturnButton;
import me.mayhem.screens.game.game.GameScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class EndGameScreenManager implements ScreenManager {

    private static final String[] TEXT_LINES = new String[]{
            "Congratulations",
            "You have beaten the game!"
    };

    private final Sprite[] sprites = new Sprite[]{UtilSharedResources.getBackground()};
    private final Interactable[] buttons = new Interactable[]{
            this.createReturnButton()
    };

    private final Sound mainTheme;
    private final GameScreenManager previousGame;

    private Text[] texts;

    public EndGameScreenManager(RenderWindow window, Sound mainTheme, GameScreenManager previousGame) {
        this.mainTheme = mainTheme;
        this.previousGame = previousGame;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
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
        for (Sprite sprite : sprites) {
            renderWindow.draw(sprite);
        }

        for (Interactable button : buttons) {
            button.draw(renderWindow);
        }

        for (Text text : texts) {
            renderWindow.draw(text);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {}

    @Override
    public Sound getSound() {
        return this.mainTheme;
    }

    private ReturnButton createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new ReturnButton(shape);
    }

    private void createText() {
        this.texts = new Text[TEXT_LINES.length];

        for (int i = 0; i < TEXT_LINES.length; i++) {
            Text text = new Text(TEXT_LINES[i], UtilSharedResources.getMainFont());
            float width = text.getLocalBounds().width;

            text.setPosition(new Vector(((Mayhem.SCREEN_WIDTH / 2f) - (width / 2)) - 20, (Mayhem.SCREEN_HEIGHT / 6f) + (50 * i)).toVector());
            text.setScale(new Vector(1, 1).toVector());
            text.setStyle(TextStyle.BOLD);
            text.setColor(Color.CYAN);

            this.texts[i] = text;
        }
    }

    public GameScreenManager getGameScreen() {
        return previousGame;
    }
}
