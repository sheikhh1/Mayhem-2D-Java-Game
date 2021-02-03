package me.mayhem.game.entity.obstacles;

import me.mayhem.Mayhem;
import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;

public class Obstacle extends Entity {

    private final Sprite sprite;

    public Obstacle(Vector position, Level level) {
        super(EntityType.SPIKES, position, Vector.getZero(), new SpriteHitbox(position, 48,48 ), Pathing.NO_PATHING);

        sprite = this.loadFromPath("obstacles/Spike.png");
        sprite.setPosition(position.toVector());

        //this.animate.setSpritePosition(position.toVector());
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.setState(EntityState.NO_MOTION);
    }

    /*public void tick() {
        this.animate.setSpritePosition(this.getPosition().toVector());
    }*/

    @Override
    public Vector getMotion() {
        return Vector.getZero();
    }

    @Override
    public void update(RenderWindow r) {
        r.draw(this.sprite);
    }

    public Sprite loadFromPath(String path) { // from KeyCard
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
