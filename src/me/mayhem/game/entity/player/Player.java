package me.mayhem.game.entity.player;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.event.PlayerJumpEvent;
import me.mayhem.game.entity.player.inventory.Inventory;
import me.mayhem.game.entity.player.inventory.Item;
import me.mayhem.game.event.EventManager;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;

/**
 * Player Class
 */
public class Player extends Entity implements PlayerInteract{

    private final String name;
    private final Clock attackedAnimateClock = new Clock();
    private final Inventory inventory = new Inventory();

    private boolean hasBeenAttacked = false;

    /**
     * Player Constructor
     * @param name - Name of Player (Assigned by user)
     * @param position - Current position of the player
     */
    public Player(String name, Vector position) {
        super(EntityType.PLAYER, position, Vector.getZero(), new SpriteHitbox(position, 55, 30), Pathing.NO_PATHING);

        this.name = name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void damage(Entity cause, double damage) {
        super.damage(cause, damage);

        this.hasBeenAttacked = true;
        this.attackedAnimateClock.restart();
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

    public void tick() {
        super.tick();

        if (this.attackedAnimateClock.getElapsedTime().asMilliseconds() >= 100 && this.hasBeenAttacked) {
            this.getAnimation().setColor(Color.WHITE);
        } else if (this.hasBeenAttacked){
            this.getAnimation().setColor(Color.RED);
        }

        if (this.isForward()) {
            this.getEntityPhysics().moveForward();
            this.animate.setRow(11);
            this.animate.setPause(false);
        } else if (this.isBack()) {
            this.getEntityPhysics().moveBack();
            this.animate.setRow(9);
            this.animate.setPause(false);
        }

        if(this.isMelee() && this.getFacing().getX() == 1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(15);
            this.animate.setPause(false);
        } else if (isMelee() && this.getFacing().getX() == -1) {
            animate.setAvailableFrames(6);
            this.animate.setRow(13);
            this.animate.setPause(false);
        }
        
        this.animate.setSpritePosition(this.getPosition().toVector());
    }

    @Override
    public void setJumping(boolean entityJump) {
        super.setJumping(entityJump);

        if (entityJump) {
            EventManager.callEvent(new PlayerJumpEvent(this));
        }
    }

    @Override
    public void attack(Entity enemy) {
        
    }

    @Override
    public void pickUp(Entity keyCard) {

    }
}
