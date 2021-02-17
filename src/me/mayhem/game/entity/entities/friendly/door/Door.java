package me.mayhem.game.entity.entities.friendly.door;


import me.mayhem.game.ai.path.impl.DoorStatePathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.physics.EntityPhysics;
import me.mayhem.game.entity.physics.NoMotionPhysics;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Door extends Entity {

    private final RectangleShape mainDoor;

    public Door(Vector position, Level level) {
        super(EntityType.DOOR, position, Vector.getZero(), new SpriteHitbox(position,130,130), new DoorStatePathing(level));

        this.setState(EntityState.NO_MOTION);

        this.mainDoor = new RectangleShape(new Vector2f(130,130));
        this.mainDoor.setPosition(position.toVector());
        this.mainDoor.setTexture(DoorState.CLOSED.getDoorTexture());
    }

    @Override
    public EntityPhysics getEntityPhysics() {
        return NoMotionPhysics.NO_MOTION;
    }

    @Override
    public void setTexture(Texture doorTexture) {
        this.mainDoor.setTexture(doorTexture);
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.mainDoor.setPosition(this.getPosition().toVector());
        renderWindow.draw(this.mainDoor);
    }
}
