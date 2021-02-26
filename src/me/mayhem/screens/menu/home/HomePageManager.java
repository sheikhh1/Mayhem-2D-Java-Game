package me.mayhem.screens.menu.home;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.home.items.HomePageLoadButton;
import me.mayhem.screens.menu.home.items.HomePageNewGameButton;
import me.mayhem.screens.menu.home.items.HomepageQuitButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

/**
 * a mamager for the main homepage of the game
 */
public class HomePageManager implements ScreenManager {

    private final RenderWindow window;

    private Sound mainTheme;
    private Interactable[] buttons;
    private Sprite[] sprites;

    /**
     * this can be called if the music has not already been initialised
     *
     * @param window the renderwindow to be used for the manager
     */
    public HomePageManager(RenderWindow window) {
        this.window = window;

        this.mainTheme = UtilSharedResources.getMainTheme();
        this.loadScreen(this.window);
    }

    /**
     * this is the constructor that you can call if the music has already been created
     *
     * @param window the window to draw the manager on
     * @param music the maintheme music of the game
     */
    public HomePageManager(RenderWindow window, Sound music) {
        this.window = window;
        this.mainTheme = music;

        this.loadScreen(this.window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    /**
     * a method that creates the sprites of the game
     */
    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        Sprite logo = UtilSharedResources.getLogo();

        if (logo == null || background == null) {
            return;
        }

        logo.setPosition(new Vector(400, 100).toVector());

        this.sprites = new Sprite[]{logo,background };
    }

    /**
     * creates the buttons that will be on the screen
     */
    private void createButtons() {
        HomepageQuitButton quit = new HomepageQuitButton(this.createQuitButton());
        HomePageNewGameButton newPage = new HomePageNewGameButton((this.createNewGameButton()));
        HomePageLoadButton load = new HomePageLoadButton((this.createLoadButton()));

        this.buttons = new Interactable[]{newPage, load, quit};
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

        for (Interactable button : this.buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {
        this.getSound().stop();
    }


    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interactable button : this.buttons) {
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
