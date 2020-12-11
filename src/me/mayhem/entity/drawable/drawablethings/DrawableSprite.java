package me.mayhem.entity.drawable.drawablethings;

import me.mayhem.entity.drawable.Drawable;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * draws a sprite onto the canvas
 */
public class DrawableSprite implements Drawable {
    private Sprite mySprite;

    /**
     *
     * @param mySprite the sprite to be drawn
     */
    public DrawableSprite(Sprite mySprite) {
        this.mySprite = mySprite;
    }

    /**
     *
     * @param path the file path of an image to to be added to the canvas
     */
    public DrawableSprite(String path) {
        try {
            Texture myTexture = new Texture();
            myTexture.loadFromFile((Paths.get(path)));
            this.mySprite = new Sprite(myTexture);

        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param canvas the canvas to draw onto
     */
    @Override
    public void draw(RenderWindow canvas) {
        canvas.draw(this.mySprite);
    }
}
