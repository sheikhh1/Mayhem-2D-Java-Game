package me.mayhem.game.entity.drawableentities.keycard;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class KeyCard extends Entity {

    private Sprite sprite;

    public KeyCard(Vector position, Attribute<?>... attributes) {
        super(EntityType.KEY_CARD, position, Vector.getZero(), new SpriteHitbox(position, 0, 0), Pathing.NO_PATHING, attributes);

        this.sprite = this.loadFromPath("interactables/keyCard.png");
    }

    @Override
    public void update(RenderWindow window) {
        window.draw(this.sprite);
    }

    public Sprite loadFromPath(String path) {
        Texture newTexture = new Texture();
        Sprite sprite = new Sprite();

        try {
            newTexture.loadFromStream(Mayhem.class.getClassLoader().getResourceAsStream(path));
            sprite.setTexture(newTexture);
            return sprite;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
