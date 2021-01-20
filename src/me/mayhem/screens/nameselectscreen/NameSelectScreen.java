package me.mayhem.screens.nameselectscreen;

import me.mayhem.Mayhem;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.nameselectscreen.items.NameSelectContinueButton;
import me.mayhem.screens.nameselectscreen.items.ReturnButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class NameSelectScreen implements ScreenManager {
    private Sound mainTheme;
    private Sprite[] sprites;
    private Interatable[] buttons;


    public NameSelectScreen(RenderWindow window, Sound mainTheme) {
        this.mainTheme = mainTheme;

        this.loadScreen(window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {

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
    public Sound getSound() {
        return null;
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createButtons() {


        NameSelectContinueButton continueButton = new NameSelectContinueButton(createContinueButton());
        ReturnButton returnButton = new ReturnButton(createReturnButton());

        buttons = new Interatable[]{continueButton, returnButton};

    }

    private Shape createContinueButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 8).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }
    private Shape createReturnButton(){

        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }
}
