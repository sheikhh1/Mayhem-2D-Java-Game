package me.mayhem.game.entity.entities.collect.key;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.entities.collect.Collectable;
import me.mayhem.game.entity.physics.type.HoverPhysics;
import me.mayhem.game.entity.player.Player;
import me.mayhem.game.entity.player.inventory.Item;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class KeyCard extends Entity implements Collectable {

    public static final String KEY_CARD_ID = "item_key_card";

    private final RectangleShape keyCard;

    public KeyCard(Vector position, Level level) {
        super(EntityType.KEY_CARD, position, Vector.getZero(), new SpriteHitbox(position, 31, 31), Pathing.HOVER_PATHING);

        super.entityPhysics = new HoverPhysics(this.getType(), this.getMotion());
        this.keyCard = new RectangleShape(new Vector2f(31, 31));
        this.keyCard.setPosition(position.toVector());
        this.keyCard.setTexture(EntityType.KEY_CARD.getEntityTexture());
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.keyCard.setPosition(this.getPosition().toVector());
        renderWindow.draw(this.keyCard);
    }

    @Override
    public void collected(Player player) {
        if (this.isDead()) {
            return;
        }

        this.setDead(true);
        player.getInventory().getItems().add(new Item(KEY_CARD_ID, EntityType.KEY_CARD.getEntitySprite(), 1));
    }
}
