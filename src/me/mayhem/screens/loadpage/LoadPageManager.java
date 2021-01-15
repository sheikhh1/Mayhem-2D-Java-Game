package me.mayhem.screens.loadpage;

import me.mayhem.input.InputListener;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.items.HomePageLoadButton;
import me.mayhem.screens.homepage.items.HomePageNewGameButton;
import me.mayhem.screens.homepage.items.HomepageQuitButton;
import me.mayhem.screens.loadpage.items.LoadPageGameSelectButton;
import me.mayhem.screens.loadpage.items.LoadPageReturnButton;
import me.mayhem.util.Vector;
import me.mayhem.util.ui.Interatable;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

public class LoadPageManager implements ScreenManager {

    private static final float HEIGHT = 800F;
    private static final float WIDTH = 1000F;
    private Interatable[] buttons;
    private Sprite[] sprites;

    public LoadPageManager(RenderWindow window){
        loadScreen(window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow){

        this.createSprites();
        this.createButtons();
        this.draw(renderWindow);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for(Sprite sprite: sprites){
            renderWindow.draw(sprite);
        }
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
    }

    @Override
    public void close(RenderWindow renderWindow) {

    }


    public Sprite spriteFromPath(String path){
        Texture newTexture = new Texture();
        try {

            newTexture.loadFromFile(Paths.get(path));
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return new Sprite(newTexture);
    }

    public void createSprites(){
        Sprite background = spriteFromPath("resources/menu/otherbackground.jpg");

        this.sprites = new Sprite[] { background};
    }


    private void createButtons() {
        //TODO : loop the button for how many saved games there are
        // store this in a list maybe?

        LoadPageGameSelectButton gameSelect = new LoadPageGameSelectButton(createGameSelectButton());
        LoadPageReturnButton returnButton= new LoadPageReturnButton(createReturnButton());

        this.buttons = new Interatable[] { gameSelect, returnButton};
    }
    /**
     * creates the load button, setting its size and position
     * @return returns the shape that is the load button
     */
    private Shape createGameSelectButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition(new Vector((WIDTH / 10) * 4, (HEIGHT / 10) * 4).toVector());
        shape.setFillColor(new Color(176,176,176));

        return shape;
    }
    /**
     * creates the quit button, setting its size and position
     * @return the shape of the button
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setPosition((0), (HEIGHT - 100));
        shape.setFillColor(new Color(176,176,176));

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            ((InputListener<?>) button).unregister();
        }
    }
}
