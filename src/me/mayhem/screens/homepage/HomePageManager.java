package me.mayhem.screens.homepage;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.items.HomePageLoadButton;
import me.mayhem.screens.homepage.items.HomePageNewGameButton;
import me.mayhem.screens.homepage.items.HomepageQuitButton;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilSprite;
import me.mayhem.util.sounds.UtilSound;
import me.mayhem.util.ui.Interatable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;


public class HomePageManager implements ScreenManager {

    private final RenderWindow window;

    private Sound mainTheme;
    private Interatable[] buttons;
    private Sprite[] sprites;

    public HomePageManager(RenderWindow window){
        this.window = window;

        this.loadScreen(this.window);
    }

    public HomePageManager(RenderWindow window, Sound music){
        this.window = window;
        this.mainTheme = music;

        this.loadScreen(this.window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.loadMusic();
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    private void loadMusic() {
        mainTheme = UtilSound.loadSoundFromPath("menu/Mainthememusic.wav");

        if (mainTheme != null) {
            mainTheme.setLoop(true);
            mainTheme.play();
        }
    }

    private void createSprites() {
        Sprite background = UtilSprite.loadFromPath("menu/otherbackground.jpg");
        Sprite logo = UtilSprite.loadFromPath("menu/mayhemLogo.png");

        if (logo == null || background == null) {
            return;
        }

        logo.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 2.0f) - 100, (0)).toVector());
        this.sprites = new Sprite[] { background, logo };
    }


    private void createButtons() {
        HomepageQuitButton quit = new HomepageQuitButton(this.createQuitButton());
        HomePageNewGameButton newPage = new HomePageNewGameButton((this.createNewGameButton()));
        HomePageLoadButton load = new HomePageLoadButton((this.createLoadButton()));

        this.buttons = new Interatable[] { newPage, load, quit };
    }

    /**
     * creates the quit button, setting its size and position
     * @return the shape of the button
     */
    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176,176,176));

        return shape;
    }
    /**
     * creates the load button, setting its size and position
     * @return returns the shape that is the load button
     */
    private Shape createLoadButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 5).toVector());
        shape.setFillColor(new Color(176,176,176));

        return shape;
    }

    /**
     * creates the new game button, setting its size and position
     * @return returns the shape that is the newgae button
     */
    private Shape createNewGameButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 3, (Mayhem.SCREEN_HEIGHT / 10f) * 2).toVector());
        shape.setFillColor(new Color(176,176,176));

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
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    public RenderWindow getWindow(){
        return this.window;
    }

    public void close(RenderWindow renderWindow){
        window.close();
    }

    @Override
    public Sound getSound() {
        return mainTheme;
    }
}
