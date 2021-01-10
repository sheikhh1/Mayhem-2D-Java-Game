package me.mayhem.screens.loadpage;

import me.mayhem.math.Vector;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.loadpage.items.LoadPageGameSelectButton;
import me.mayhem.screens.loadpage.items.LoadPageReturnButton;
import me.mayhem.ui.Interatable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.VideoMode;

public class LoadPageManager implements ScreenManager {

    private static final float HEIGHT = 800F;
    private static final float WIDTH = 1000F;
    private Interatable[] buttons;

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        renderWindow.create(new VideoMode((int) WIDTH, (int) HEIGHT), "Mayhem");

        this.createButtons();
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
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
        shape.setOrigin(new Vector((WIDTH / 10) * 4, (HEIGHT / 10) * 4).toVector());

        return shape;
    }
    /**
     * creates the quit button, setting its size and position
     * @return the shape of the button
     */
    private Shape createReturnButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(200,100).toVector());
        shape.setOrigin((0), (HEIGHT - 100));

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            button.unregister();
        }
    }
}
