package me.mayhem.screens.loadpage;

import me.mayhem.Mayhem;
import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.loadpage.items.*;
import me.mayhem.util.UtilSharedResources;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interactable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.w3c.dom.css.Rect;

import javax.swing.*;

public class LoadPageManager implements ScreenManager {

    private final Sound mainTheme;

    private Interactable[] buttons;
    private Sprite[] sprites;
    private LoadGameButtons saves;
    private int savepage = 0;
    private RectangleShape whitespace;


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

        LoadPageReturnButton returnButton = new LoadPageReturnButton(createReturnButton());
        NextButton next = new NextButton(createNextButton());
        PrevButton prev = new PrevButton(createPrevButton());


        this.saves = new LoadGameButtons();

        this.buttons = new Interactable[]{returnButton,next,prev};
    }

    /**
     * creates the return button, setting its size and position
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
    private Shape createPrevButton(){
        RectangleShape rect = new RectangleShape();

        rect.setPosition(new Vector(200,600).toVector());
        rect.setSize(new Vector(200,100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return rect;

    }
    private Shape createNextButton(){
        RectangleShape rect = new RectangleShape();

        rect.setPosition(new Vector(600,600).toVector());
        rect.setSize(new Vector(200,100).toVector());
        rect.setFillColor(new Color(176, 176, 176));

        return rect;
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

        saves.draw(renderWindow, savepage);
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }
    @Override
    public Sound getSound() {
        return mainTheme;
    }

    public void setSavepage(int savepage) {
        this.savepage = savepage;
    }
    public int getSavepage(){
        return savepage;
    }
    private RectangleShape createBackgroundshape(){
        RectangleShape rect = new RectangleShape();

        rect.setPosition(new Vector(200,100).toVector());
        rect.setSize(new Vector(600,600).toVector());
        rect.setFillColor(Color.BLUE);

        return rect;
    }
    public int getMaxSavepages(){
        System.out.println("number of save pages " + Math.ceil(saves.getNumberOfSave()/3));
        return (int) Math.ceil(saves.getNumberOfSave()/3);
    }

    public void setpagechange(boolean pagechange){
        this.saves.setPageChange(pagechange);
    }
    public boolean getPageChange(){
        return this.saves.isPageChange();
    }
}
