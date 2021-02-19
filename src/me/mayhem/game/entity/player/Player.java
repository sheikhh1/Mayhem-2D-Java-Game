package me.mayhem.game.entity.player;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.event.PlayerJumpEvent;
import me.mayhem.game.entity.player.inventory.Inventory;
import me.mayhem.game.entity.player.inventory.Item;
import me.mayhem.game.event.EventManager;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import me.mayhem.util.direction.UtilVector;
import org.jsfml.graphics.RenderWindow;

/**
 * Player Class
 */
public class Player extends Entity {

    private final Inventory inventory = new Inventory();

    private final String name;
    private final Level level;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position, Level level) {
        super(EntityType.PLAYER, position, Vector.getZero(), new SpriteHitbox(position, 55, 30), Pathing.NO_PATHING);

        this.name = name;
        this.level = level;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void update(RenderWindow window) {
        super.update(window);

        Vector startPosition = new Vector(0, 0);

        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            Item item = this.inventory.getItems().get(i);

            item.draw(window, startPosition.add(i * 31, 0).clone());
        }
    }

    @Override
    public void setJumping(boolean entityJump) {
        super.setJumping(entityJump);

        if (entityJump) {
            EventManager.callEvent(new PlayerJumpEvent(this));
        }
    }

    @Override
    public void setMelee(boolean entityMelee) {
        super.setMelee(entityMelee);

        if (!entityMelee) {
            return;
        }

        double closestEntity = Integer.MAX_VALUE;
        Entity closest = null;

        for (Entity nearbyEntity : this.level.getNearbyEntities(this, 40000)) {
            if (UtilVector.inSight(this, nearbyEntity)) {
                double distance = UtilVector.getDistanceSquared(this.getPosition(), nearbyEntity.getPosition());

                if (distance < closestEntity) {
                    closestEntity = distance;
                    closest = nearbyEntity;
                }
            }
        }

        if (closest != null) {
            this.attack(closest);
        }
    }

    public void attack(Entity enemy) {
        enemy.damage(this, 1);
    }
}
