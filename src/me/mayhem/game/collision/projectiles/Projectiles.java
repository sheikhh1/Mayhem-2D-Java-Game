package me.mayhem.game.collision.projectiles;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.attribute.Attribute;
import me.mayhem.game.collision.Hitbox;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class Projectiles extends Entity {
    private Sprite sprite;

    public Projectiles(EntityType type, Vector position, Vector motion,String image, Hitbox hitbox, Pathing pathing, Attribute<?>... attributes) {
        super(EntityType.PROJECTILE, position, Vector.ZERO, new SpriteHitbox(position, 0, 0), Pathing.NO_PATHING, attributes);

        this.sprite = this.loadFromPath(image);
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
