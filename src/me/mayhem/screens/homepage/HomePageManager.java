package me.mayhem.screens.homepage;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.items.HomePageLoadButton;
import me.mayhem.screens.homepage.items.HomePageNewGameButton;
import me.mayhem.screens.homepage.items.HomepageQuitButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


public class HomePageManager implements ScreenManager {

    private final RenderWindow window;

    private Sound mainTheme;
    private Interatable[] buttons;
    private Sprite[] sprites;
    private Difficulty difficulty;

    public HomePageManager(RenderWindow window) {
        this.window = window;

        mainTheme = UtilSharedResources.getMainTheme();
        this.loadScreen(this.window);
    }

    public HomePageManager(RenderWindow window, Sound music, Difficulty difficulty) {
        this.window = window;
        this.mainTheme = music;
        this.difficulty = difficulty;

        this.loadScreen(this.window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        Sprite logo = UtilSharedResources.getLogo();

        if (logo == null || background == null) {
            return;
        }

        logo.setPosition(new Vector((0), (0)).toVector());

        this.sprites = new Sprite[]{logo,background };
    }


    private void createButtons() {
        HomepageQuitButton quit = new HomepageQuitButton(this.createQuitButton());
        HomePageNewGameButton newPage = new HomePageNewGameButton((this.createNewGameButton()));
        HomePageLoadButton load = new HomePageLoadButton((this.createLoadButton()));

        this.buttons = new Interatable[]{newPage, load, quit};
    }

    /**
     * creates the quit button, setting its size and position
     *
     * @return the shape of the button
     */
    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     * creates the load button, setting its size and position
     *
     * @return returns the shape that is the load button
     */
    private Shape createLoadButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 5).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     * creates the new game button, setting its size and position
     *
     * @return returns the shape that is the newgae button
     */
    private Shape createNewGameButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 2).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Sprite sprite : this.sprites) {
            window.draw(sprite);
        }

        for (Interatable button : this.buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    public RenderWindow getWindow() {
        return this.window;
    }

    @Override
    public Sound getSound() {
        return mainTheme;
    }
}
