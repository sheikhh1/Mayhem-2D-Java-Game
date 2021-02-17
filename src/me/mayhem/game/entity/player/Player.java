package me.mayhem.game.entity.player;

import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.player.event.PlayerJumpEvent;
import me.mayhem.game.entity.player.inventory.Inventory;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.event.EventManager;
import me.mayhem.util.Vector;
import org.jsfml.graphics.Color;
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

        this.animate.setSpritePosition(position.toVector());
        this.name = name;
        this.setState(EntityState.FALLING);
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

    public String getName(){
       return name;
    }

    @Override
    public void attack(Entity enemy) {
        
    }

    @Override
    public void pickUp(Entity keyCard) {

    }
}
