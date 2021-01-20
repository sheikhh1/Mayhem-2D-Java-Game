package me.mayhem.screens.loadpage;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.loadpage.items.LoadPageGameSelectButton;
import me.mayhem.screens.loadpage.items.LoadPageReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class LoadPageManager implements ScreenManager {

    private final Sound mainTheme;

    private Interatable[] buttons;
    private Sprite[] sprites;

    public LoadPageManager(RenderWindow window, Sound mainTheme) {
        this.mainTheme = mainTheme;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createButtons() {
        //TODO : loop the button for how many saved games there are
        // store this in a list maybe?

        LoadPageGameSelectButton gameSelect = new LoadPageGameSelectButton(createGameSelectButton());
        LoadPageReturnButton returnButton = new LoadPageReturnButton(createReturnButton());

        this.buttons = new Interatable[]{gameSelect, returnButton};
    }

    /**
     * creates the load button, setting its size and position
     *
     * @return returns the shape that is the load button
     */
    private Shape createGameSelectButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 4).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     * creates the quit button, setting its size and position
     *
     * @return the shape of the button
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Sprite sprite : this.sprites) {
            renderWindow.draw(sprite);
        }

        for (Interatable button : this.buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }

    @Override
    public Sound getSound() {
        return mainTheme;
    }
}
