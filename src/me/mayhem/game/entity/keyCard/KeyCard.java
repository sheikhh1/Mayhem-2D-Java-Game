package me.mayhem.game.entity.keyCard;

import me.mayhem.Mayhem;
import me.mayhem.game.entity.drawable.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class KeyCard implements Drawable {

    @Override
    public void draw(RenderWindow canvas) {

    }
    public static keyCard loadFromPath("keyCard.png") {
        Texture newTexture = new Texture();

        try {
            newTexture.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            return new KeyCard(newTexture);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
