package me.mayhem.game.entity.state;

public enum EntityState {

    FORWARD(1),
    BACK(1),
    STANDING(1),
    RANGEATTACK(1),
    MELEE(1),
    JUMPING(0),
    FALLING(0),
    NO_MOTION(0),

    ;

    private int index;

    EntityState(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
