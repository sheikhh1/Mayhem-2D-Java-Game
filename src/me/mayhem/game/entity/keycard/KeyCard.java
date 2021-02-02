package me.mayhem.game.entity.keycard;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.physics.HoverPhysics;
import me.mayhem.util.Vector;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class KeyCard extends Entity {

    private RectangleShape keyCard;
    private Vector keyCardPosition;

    public KeyCard(Vector position) {
        super(EntityType.KEYCARD, position, Vector.getZero(), new SpriteHitbox(position, 31, 31), Pathing.HOVER_PATHING);
        super.entityPhysics = new HoverPhysics();
        this.keyCardPosition = position;
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.keyCard = new RectangleShape(new Vector2f(31, 31));
        this.keyCard.setPosition(position.toVector());
        this.keyCard.setTexture(EntityType.KEYCARD.getEntityTexture());
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.keyCard.setPosition(this.keyCardPosition.toVector());
        renderWindow.draw(this.keyCard);
    }


}
