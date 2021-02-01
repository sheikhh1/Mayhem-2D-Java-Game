package me.mayhem.screens.newgamesettingspage;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageEasyButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageHardButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageMediumButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class NewGameSettingsPageManager implements ScreenManager {

    private final Sound mainTheme;

    private Interactable[] buttons;
    private Sprite[] sprites;


    public NewGameSettingsPageManager(RenderWindow window, Sound mainTheme){
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

    /**
     * creates all the buttons that the screen is going to display
     */
    private void createButtons() {
        // each difficult button and return
        SettingsPageReturnButton returnButton = new SettingsPageReturnButton(createReturnButton());
        SettingsPageEasyButton easy = new SettingsPageEasyButton(createEasyButton());
        SettingsPageMediumButton medium = new SettingsPageMediumButton(createMediumButton());
        SettingsPageHardButton hard = new SettingsPageHardButton(createHardButton());

        this.buttons = new Interactable[] {easy, medium, hard, returnButton };
    }

    /**
     *Creates the shape for the return button, including size and position
     * @return returns the shape that is the return button
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     *Creates the shape for the easy button, including size and position
     * @return returns the shape that is the easy button
     */
    private Shape createEasyButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     *Creates the shape for the medium button, including size and position
     * @return returns the shape that is the medium button
     */
    private Shape createMediumButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f) * 4);
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    /**
     *Creates the shape for the hard button, including size and position
     * @return returns the shape that is the Hard button
     */
    private Shape createHardButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH / 10f * 4), (Mayhem.SCREEN_HEIGHT / 10f) * 7);
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
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
    public void close(RenderWindow renderWindow) {}


    @Override
    public Sound getSound() {
        return mainTheme;
    }
}
