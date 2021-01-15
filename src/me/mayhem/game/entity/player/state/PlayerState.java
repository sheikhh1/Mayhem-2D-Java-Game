package me.mayhem.game.entity.player.state;

public enum PlayerState {

    FORWARD(1),
    BACK(1),
    STANDING(1),
    JUMPING(0),
    FALLING(0),
    NO_MOTION(0),

    ;

    private int index;

    PlayerState(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
