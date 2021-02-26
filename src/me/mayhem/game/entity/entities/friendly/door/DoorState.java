package me.mayhem.game.entity.entities.friendly.door;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum DoorState {

    OPEN(1, "interactables/doors/DoorOpen.png"),
    CLOSED(0, "interactables/doors/DoorClosed.png"),
    OPENING(-1, "interactables/doors/DoorOpening.png")
    ;

    private final int doorState;
    private final Texture doorTexture;

    DoorState(int doorState, String imagePath) {
        this.doorState = doorState;
        this.doorTexture = UtilImageLoader.loadTextureFromStream(imagePath);
    }

    public int getDoorState() {
        return this.doorState;
    }

    public Texture getDoorTexture() {
        return this.doorTexture;
    }

}
