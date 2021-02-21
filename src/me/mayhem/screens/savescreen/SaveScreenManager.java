package me.mayhem.screens.savescreen;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.escapescreen.EscapeScreenManager;
import me.mayhem.screens.escapescreen.items.ReturnToGameButton;
import me.mayhem.screens.escapescreen.items.ReturnToMainMenuButton;
import me.mayhem.screens.gamescreen.GameScreenManager;
import me.mayhem.screens.nameselectscreen.items.InputBox;
import me.mayhem.screens.savescreen.items.ReturnButton;
import me.mayhem.screens.savescreen.items.SaveButton;
import me.mayhem.screens.winscreen.WinScreenManager;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.file.UtilFont;
import me.mayhem.util.ui.Interactable;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

public class SaveScreenManager implements ScreenManager {

    private WinScreenManager prevScreen;
    private Sound mainTheme;
    private Text text;

    private Sprite[] sprites;
    private Interactable[] buttons;

    public SaveScreenManager(RenderWindow window, Sound sound, WinScreenManager prev){

        this.prevScreen = prev;
        this.mainTheme = sound;

        this.loadScreen(window);
    }

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        this.createSprites();
        this.createButtons();
        this.text = createText();
        this.draw(renderWindow);
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {

        if (this.mainTheme != null) {
            this.mainTheme.stop();
        }

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
        renderWindow.draw(text);
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }

    @Override
    public Sound getSound() {
        return null;
    }

    // create methods
    private void createSprites() {
        Sprite background = UtilSharedResources.getBackground();

        this.sprites = new Sprite[]{background};
    }

    private void createButtons() {
        ReturnButton toGame = new ReturnButton(createReturnButton());
        SaveButton save = new SaveButton(createSaveButton());
        InputBox input = createInput();


        buttons = new Interactable[]{toGame, save , input};
    }

    private Shape createReturnButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private Shape createSaveButton(){

        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 4, (Mayhem.SCREEN_HEIGHT / 10f) * 4).toVector());
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    private InputBox createInput() {
        InputBox inputBox = new InputBox(new Vector((Mayhem.SCREEN_WIDTH / 10f) * 6, (Mayhem.SCREEN_HEIGHT / 10f)).toVector());

        inputBox.setPosition(new Vector(200, 250));

        return inputBox;
    }
    public Text createText(){

        Text text = new Text("Please insert a file name", UtilFont.loadFont("fonts/FreeSans.ttf"));
        text.setPosition(new Vector(325, 200).toVector());

        return text;
    }
    public InputBox getBox(){
        return (InputBox) buttons[2];
    }

    public Text getText() {
        return text;
    }

    public WinScreenManager getPrevScreen() {
        return prevScreen;
    }
}