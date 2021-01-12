package me.mayhem.screens.newgamesettingspage;

import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageEasyButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageHardButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageMediumButton;
import me.mayhem.screens.newgamesettingspage.items.SettingsPageReturnButton;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;

public class NewGameSettingsPageManager implements ScreenManager {
    private static final float HEIGHT = 800F;
    private static final float WIDTH = 1000F;
    private Interatable[] buttons;

    public NewGameSettingsPageManager(RenderWindow window){
        loadScreen(window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {

        this.createButtons();
        this.draw(renderWindow);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {
        renderWindow.close();
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

        this.buttons = new Interatable[] {easy, medium, hard, returnButton };
    }
    /**
     *Creates the shape for the return button, including size and position
     * @return returns the shape that is the return button
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition((0), (HEIGHT - 100));
        shape.setFillColor(Color.RED);

        return shape;
    }
    /**
     *Creates the shape for the easy button, including size and position
     * @return returns the shape that is the easy button
     */
    private Shape createEasyButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition((WIDTH / 10 * 4), (HEIGHT /10));
        shape.setFillColor(Color.GREEN);

        return shape;
    }
    /**
     *Creates the shape for the medium button, including size and position
     * @return returns the shape that is the medium button
     */
    private Shape createMediumButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition((WIDTH / 10 * 4), (HEIGHT/10) * 4);
        shape.setFillColor(Color.YELLOW);

        return shape;
    }
    /**
     *Creates the shape for the hard button, including size and position
     * @return returns the shape that is the Hard button
     */
    private Shape createHardButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition((WIDTH / 10 * 4), (HEIGHT/10) * 7);
        shape.setFillColor(Color.RED);

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }
}
