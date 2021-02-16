package me.mayhem.game.entity.entities.friendly.door;

import me.mayhem.util.file.UtilImageLoader;
import org.jsfml.graphics.Texture;

public enum DoorEnum {

    OPEN(1, "interactables/doors/DoorOpen.png"),
    CLOSED(0, "interactables/doors/DoorClosed.png"),
    OPENING(-1, "interactables/doors/DoorOpening.png")
    ;

    private int doorState;
    private String imagePath;
    private final Texture doorTexture;


    DoorEnum(int doorState, String imagePath) {
        this.doorState = doorState;
        this.imagePath = imagePath;
        this.doorTexture = UtilImageLoader.loadTextureFromStream(getClass().getClassLoader().getResourceAsStream(this.imagePath));
    }

    public int getDoorState() {
        return this.doorState;
    }

    public Texture getDoorTexture() {
        return this.doorTexture;
    }

}
