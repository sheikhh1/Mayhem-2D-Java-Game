package me.mayhem.screens.homepage;

import me.mayhem.math.Vector;
import me.mayhem.screens.ScreenManager;
import me.mayhem.screens.homepage.items.HomePageLoadButton;
import me.mayhem.screens.homepage.items.HomePageNewGameButton;
import me.mayhem.screens.homepage.items.HomepageQuitButton;
import me.mayhem.ui.Interatable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shape;
import org.jsfml.window.VideoMode;

public class HomePageManager implements ScreenManager {

    private static final float HEIGHT = 800F;
    private static final float WIDTH = 1000F;

    private Interatable[] buttons;

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        renderWindow.create(new VideoMode((int) WIDTH, (int) HEIGHT), "Mayhem");

        this.createButtons();
        this.drawButtons(renderWindow);
    }

    private void drawButtons(RenderWindow renderWindow){
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
    }

    private void createButtons(){
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

        shape.setSize(new Vector(200,100).toVector());
        shape.setOrigin((WIDTH / 10) * 4, (HEIGHT / 10) * 7);

        return shape;
    }

    /**
     * creates the load button, setting its size and position
     * @return returns the shape that is the load button
     */
    private Shape createLoadButton() {
        RectangleShape shape = new RectangleShape();

        shape.setOrigin(100, 100);
        shape.setSize(new Vector((WIDTH / 10) * 4, (HEIGHT / 10) * 4).toVector());

        return shape;
    }

    /**
     * creates the new game button, setting its size and position
     * @return returns the shape that is the newgae button
     */
    private Shape createNewGameButton() {
        RectangleShape shape = new RectangleShape();

        shape.setOrigin(100, 100);
        shape.setSize(new Vector((WIDTH / 10) * 4, (HEIGHT / 10)).toVector());

        return shape;
    }

    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        renderWindow.close();
    }
}
