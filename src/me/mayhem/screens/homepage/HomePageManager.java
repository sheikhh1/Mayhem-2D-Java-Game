package me.mayhem.screens.homepage;

import me.mayhem.math.Vector;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.items.HomePageLoadButton;
import me.mayhem.screens.homepage.items.HomePageNewGameButton;
import me.mayhem.screens.homepage.items.HomepageQuitButton;
import me.mayhem.ui.Interatable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;

public class HomePageManager implements ScreenManager {

    private static final float HEIGHT = 800F;
    private static final float WIDTH = 1000F;
    private RenderWindow window;
    private Interatable[] buttons;
    public HomePageManager(RenderWindow window){
        this.window = window;
        loadScreen(this.window);
    }
    @Override
    public void loadScreen(RenderWindow renderWindow) {
        renderWindow.create(new VideoMode((int) WIDTH, (int) HEIGHT), "Mayhem");

        this.createButtons();
        this.draw(renderWindow);
    }

    @Override
    public void draw(RenderWindow renderWindow) {
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
    }
    private void createButtons() {
        HomepageQuitButton quit = new HomepageQuitButton(this.createQuitButton());
        HomePageNewGameButton newPage = new HomePageNewGameButton((this.createNewGameButton()));
        HomePageLoadButton load = new HomePageLoadButton((this.createLoadButton()));

        this.buttons = new Interatable[] { newPage, load, quit };
    }
    /**
     * creates the quit button, setting its size and position
     * @return the shape of the button
     */
    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((WIDTH / 10) * 3, (HEIGHT / 10) * 7).toVector());
        shape.setFillColor(Color.RED);

        return shape;
    }
    /**
     * creates the load button, setting its size and position
     * @return returns the shape that is the load button
     */
    private Shape createLoadButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((WIDTH / 10) * 3, (HEIGHT / 10) * 4).toVector());
        shape.setFillColor(Color.CYAN);

        return shape;
    }

    /**
     * creates the new game button, setting its size and position
     * @return returns the shape that is the newgae button
     */
    private Shape createNewGameButton() {
        RectangleShape shape = new RectangleShape();

        shape.setSize(new Vector(400,100).toVector());
        shape.setPosition(new Vector((WIDTH / 10) * 3, (HEIGHT / 10)).toVector());
        shape.setFillColor(Color.MAGENTA);

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        for (Interatable button : this.buttons) {
            button.unregister();
        }
    }
    public RenderWindow getWindow(){
        return this.window;
    }
}
