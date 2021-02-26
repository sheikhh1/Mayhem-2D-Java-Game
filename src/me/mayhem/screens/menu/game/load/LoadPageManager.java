package me.mayhem.screens.menu.game.load;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.save.SaveData;
import me.mayhem.save.SaveFileManager;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.menu.game.load.items.LoadGameFileButton;
import me.mayhem.screens.menu.game.load.items.LoadPageReturnButton;
import me.mayhem.screens.menu.game.load.items.NextPageButton;
import me.mayhem.screens.menu.game.load.items.PreviousPageButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This page manager displays the save files and allows the user to select a saved game and play it
 *
 */
public class LoadPageManager implements ScreenManager {

    private final Sprite[] sprites = new Sprite[] {UtilSharedResources.getBackground()};

    private final Sound mainTheme;
    private final int page;
    private final List<Interactable> buttons = new ArrayList<>();

    /**
     *
     * Default constructor passing the page as 0 for default (test map)
     *
     * @param window The window to draw on
     * @param mainTheme The main theme to play
     */
    public LoadPageManager(RenderWindow window, Sound mainTheme) {
        this(window, mainTheme, 0);
    }

    /**
     *
     * The constructor taking the page to display the saves from
     *
     * @param window The window to draw on
     * @param mainTheme The main theme to play
     * @param page the page to show saves from
     */
    public LoadPageManager(RenderWindow window, Sound mainTheme, int page) {
        this.mainTheme = mainTheme;
        this.page = page;

        this.loadScreen(window);
    }

    /**
     *
     * The current page
     *
     * @return The current page
     */
    public int getPage() {
        return this.page;
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.loadPageFiles();
        this.draw(renderWindow);
    }

    /**
     * method to load in the save instance buttons
     */
    private void loadPageFiles() {
        this.buttons.add(this.createNextButton());
        this.buttons.add(this.createPrevButton());
        this.buttons.add(this.createReturnButton());

        for (int i = 0; i < 3; i++) {
            int currentIndex = (this.page * 3) + i;
            if (SaveFileManager.getSaveFiles().size() <= currentIndex) {
                break;
            }

            SaveData saveData = SaveFileManager.getSaveFiles().get(currentIndex);
            this.buttons.add(new LoadGameFileButton(this.createLoadGameShape(new Vector(400, 100 + i * 200)), saveData));
        }
    }

    /**
     * creates the shape that represents the loadgame shapes
     *
     * @param position the posititon in the
     * @return
     */
    private Shape createLoadGameShape(Vector position) {
        RectangleShape rect = new RectangleShape();

        rect.setPosition(position.toVector());
        rect.setSize(new Vector(200, 100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return rect;
    }

    /**
     * creates the return button, setting its size and position
     *
     * @return the shape of the button
     */
    private LoadPageReturnButton createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return new LoadPageReturnButton(shape);
    }

    private PreviousPageButton createPrevButton() {
        RectangleShape rect = new RectangleShape();

        rect.setPosition(new Vector(200, 600).toVector());
        rect.setSize(new Vector(200, 100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return new PreviousPageButton(rect);
    }

    private NextPageButton createNextButton() {
        RectangleShape rect = new RectangleShape();

        rect.setPosition(new Vector(600, 600).toVector());
        rect.setSize(new Vector(200, 100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return new NextPageButton(rect);
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
        return this.mainTheme;
    }
}
