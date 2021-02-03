package me.mayhem.game.entity;


import me.mayhem.game.ai.path.Pathing;
import me.mayhem.game.collision.impl.SpriteHitbox;
import me.mayhem.util.Vector;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class Door extends Entity {

    private RectangleShape mainDoor;
    private Vector doorPosition;

    public Door(Vector position) {
        super(EntityType.DOOR, position, Vector.getZero(), new SpriteHitbox(position,50,50), Pathing.NO_PATHING);
        this.doorPosition = position;
        this.mainDoor = new RectangleShape(new Vector2f(50,50));
        this.mainDoor.setPosition(position.toVector());
        this.mainDoor.setTexture(EntityType.DOOR.getEntityTexture());
    }

    @Override
    public void update(RenderWindow renderWindow) {
        this.mainDoor.setPosition(this.doorPosition.toVector());
        renderWindow.draw(this.mainDoor);
    }
}
