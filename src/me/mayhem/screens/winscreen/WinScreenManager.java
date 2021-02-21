package me.mayhem.screens.winscreen;

import jdk.jshell.execution.Util;
import me.mayhem.Mayhem;
import me.mayhem.game.GameManager;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.winscreen.items.WinContinueButton;
import me.mayhem.screens.winscreen.items.WinQuitButton;
import me.mayhem.screens.winscreen.items.WinSaveButton;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import me.mayhem.util.ui.impl.ButtonInteractable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

import javax.security.auth.login.CredentialNotFoundException;

public class WinScreenManager implements ScreenManager {
    private Sound mainTheme;
    private GameManager prev;

    private Interactable[] buttons;
    private Sprite[] sprites;


    public WinScreenManager(RenderWindow window, Sound maintheme, GameManager prev){
        this.mainTheme = maintheme;
        this.prev = prev;

        loadScreen(window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        createButtons();
        createSprites();

        draw(renderWindow);
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interactable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Sprite sprite: sprites){
            renderWindow.draw(sprite);
        }
        for (Interactable button: buttons){
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
    public void createButtons(){
        WinQuitButton quit = new WinQuitButton(createQuitButton());
        WinContinueButton continueButton = new WinContinueButton(createContinueButton());
        WinSaveButton save = new WinSaveButton(createSaveButton());

        buttons = new Interactable[]{quit,continueButton, save};
    }
    public Shape createQuitButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((0), (Mayhem.SCREEN_HEIGHT - 100));
        shape.setFillColor(new Color(176, 176, 176));

        return shape;

    }
    public Shape createContinueButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH/10f)* 4, (Mayhem.SCREEN_HEIGHT/8f) * 2);
        shape.setFillColor(new Color(176, 176, 176));

        return shape;

    }
    public Shape createSaveButton(){
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200, 100).toVector());
        shape.setPosition((Mayhem.SCREEN_WIDTH/10f)* 4, (Mayhem.SCREEN_HEIGHT/8f) * 5);
        shape.setFillColor(new Color(176, 176, 176));

        return shape;
    }

    public void createSprites(){
        Sprite backGround = UtilSharedResources.getBackground();

        sprites = new Sprite[]{backGround};

    }
}
