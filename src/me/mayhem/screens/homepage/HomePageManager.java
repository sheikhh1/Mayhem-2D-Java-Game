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

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;

    private Interatable[] buttons;

    @Override
    public void loadScreen(RenderWindow renderWindow) {
        renderWindow.create(new VideoMode(WIDTH, HEIGHT), "Mayhem");
        CreateButtons();
        DrawButtons(renderWindow);
    }


    private void DrawButtons(RenderWindow renderWindow){
        for (Interatable button : buttons) {
            button.draw(renderWindow);
        }
    }
    private void CreateButtons(){
        quit = new HomepageQuitButton(this.createQuitButton());
        newPage = new HomePageNewGameButton((this.createNewGameButton()));
        load = new HomePageLoadButton((this.createLoadButton()));

        buttons = new Interatable[]{newPage,load,quit};
    }
    /**
     * creates the quit button, setting its size and position
     * @return the shape of the button
     */
    private Shape createQuitButton() {
        RectangleShape shape = new RectangleShape();
        shape.setSize(new Vector(200,100).toVector());
        shape.setOrigin((float) (WIDTH /10.00 * 4), (float) (HEIGHT /10.00 * 7));

        return shape;
    }

    /**
     * creates the load button, setting its size and position
     * @return returns the shape that is the load button
     */
    private Shape createLoadButton() {
        RectangleShape shape = new RectangleShape();
        shape.setOrigin(100, 100);
        shape.setSize(new Vector((float) (WIDTH /10.00 * 4), (float) (HEIGHT /10.00 * 4)).toVector());
        return shape;
    }

    /**
     * creates the new game button, setting its size and position
     * @return returns the shape that is the newgae button
     */
    private Shape createNewGameButton() {
        RectangleShape shape = new RectangleShape();
        shape.setOrigin(100, 100);
        shape.setSize(new Vector((float) (WIDTH /10.00 * 4), (float) (HEIGHT /10.00)).toVector());
        return shape;
    }


    @Override
    public void unloadScreen(RenderWindow renderWindow) {
        renderWindow.close();
    }
}
