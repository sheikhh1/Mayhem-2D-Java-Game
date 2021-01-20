package me.mayhem.screens.escapescreen;

import me.mayhem.Mayhem;
import me.mayhem.game.level.difficulty.Difficulty;
import me.mayhem.screens.ScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class EscapeScreenManager implements ScreenManager {
    private Sprite[] sprites;
    private ButtonInteractable[] buttons;

    @Override
    public void loadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {

    }

    @Override
    public void draw(RenderWindow renderWindow) {

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
        return null;
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }
    private void createButtons(){

    }

    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private Shape createSaveButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }
}
