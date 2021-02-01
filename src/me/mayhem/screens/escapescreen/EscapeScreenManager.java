package me.mayhem.screens.escapescreen;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.escapescreen.items.ReturnToGameButton;
import me.mayhem.screens.escapescreen.items.ReturnToMainMenuButton;
import me.mayhem.screens.escapescreen.items.SaveGameButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class EscapeScreenManager implements ScreenManager {

    private Sprite[] sprites;
    private ButtonInteractable[] buttons;
    private ScreenManager prevScreen;
    private Sound mainTheme;

    public EscapeScreenManager(RenderWindow window, Sound sound, ScreenManager prev){
        this.prevScreen = prev;
        this.mainTheme = sound;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
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
    public Sound getSound() {
        return null;
    }

    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createButtons(){
        ReturnToGameButton toGame = new ReturnToGameButton(createReturnButton());
        SaveGameButton save = new SaveGameButton(createSaveButton());
        ReturnToMainMenuButton main = new ReturnToMainMenuButton(createQuitButton());

        buttons = new ButtonInteractable[]{toGame,save,main};
    }

    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH/ 10f)* 4, (Mayhem.SCREEN_HEIGHT/10f ) * 2).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private Shape createSaveButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH/10f)* 4, (Mayhem.SCREEN_HEIGHT/10f ) * 6).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    public ScreenManager getPrevScreen() {
        return prevScreen;
    }
}
