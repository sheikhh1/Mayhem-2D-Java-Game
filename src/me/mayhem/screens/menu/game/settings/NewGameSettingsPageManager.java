package me.mayhem.screens.menu.game.settings;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.game.settings.items.*;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

/**
 * this page allows you to set the difficulty for thw upcomming game
 */
public class NewGameSettingsPageManager implements ScreenManager {

    private final Sprite[] sprites = new Sprite[] { UtilSharedResources.getBackground() };
    private final Interactable[] buttons = new Interactable[]{
            this.createEasyButton(),
            this.createMediumButton(),
            this.createHardButton(),
            this.createReturnButton(),
            this.createTutorialButton()
    };

    private final Sound mainTheme;


    public NewGameSettingsPageManager(RenderWindow window, Sound mainTheme) {
        this.mainTheme = mainTheme;
        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.draw(renderWindow);
    }

    /**
     * Creates the shape for the return button, including size and position
     *
     * @return returns the shape that is the return button
     */
    private SettingsPageReturnButton createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new SettingsPageReturnButton(shape);
    }

    /**
     * Creates the shape for the easy button, including size and position
     *
     * @return returns the shape that is the easy button
     */
    private SettingsPageEasyButton createEasyButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f));
        shape.setFillColor(new Color(176, 176, 176));

        return new SettingsPageEasyButton(shape);
    }

    /**
     * Creates the shape for the medium button, including size and position
     *
     * @return returns the shape that is the medium button
     */
    private SettingsPageMediumButton createMediumButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f) * 4);
        shape.setFillColor(new Color(176, 176, 176));

        return new SettingsPageMediumButton(shape);
    }

    /**
     * Creates the shape for the hard button, including size and position
     *
     * @return returns the shape that is the Hard button
     */
    private SettingsPageHardButton createHardButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f) * 7);
        shape.setFillColor(new Color(176, 176, 176));

        return new SettingsPageHardButton(shape);
    }

    public SettingsPageTutorialButton createTutorialButton() {
        CircleShape circle = new CircleShape();

        circle.setRadius(50);
        circle.setPosition(new Vector(Mayhem.SCREEN_WIDTH - 150, 100).toVector());
        circle.setFillColor(new Color(176, 176, 176));

        return new SettingsPageTutorialButton(circle);
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
        return mainTheme;
    }
}
