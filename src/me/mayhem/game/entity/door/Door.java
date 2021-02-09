package me.mayhem.game.entity.door;


import me.mayhem.game.ai.path.impl.DoorStatePathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.game.entity.Entity;
import me.mayhem.game.entity.EntityType;
import me.mayhem.game.entity.state.EntityState;
import me.mayhem.game.level.Level;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Door extends Entity {

    private RectangleShape mainDoor;
    private Vector doorPosition;

    public Door(Vector position, Level level) {
        super(EntityType.DOOR, position, Vector.getZero(), new SpriteHitbox(position,130,130), new DoorStatePathing(level));
        this.doorPosition = position;
        this.setState(EntityState.NO_MOTION);
        this.getEntityPhysics().setEntityMotion(this.getMotion());
        this.mainDoor = new RectangleShape(new Vector2f(130,130));
        this.mainDoor.setPosition(position.toVector());
        this.mainDoor.setTexture(DoorEnum.CLOSED.getDoorTexture());
    }

    @Override
    public void setTexture(Texture doorTexture) {
        this.mainDoor.setTexture(doorTexture);
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.mainDoor.setPosition(this.doorPosition.toVector());
        renderWindow.draw(this.mainDoor);
    }
}
