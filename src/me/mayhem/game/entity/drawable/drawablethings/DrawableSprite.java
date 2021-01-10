package me.mayhem.game.entity.drawable.drawablethings;

import me.mayhem.game.entity.drawable.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

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
        Texture myTexture = new Texture();
        this.loadFromFile(myTexture, path);
        this.mySprite = new Sprite(myTexture);
    }

    private void loadFromFile(Texture texture, String path) {
        try {
            texture.loadFromFile(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(RenderWindow canvas) {
        canvas.draw(this.mySprite);
    }
}
