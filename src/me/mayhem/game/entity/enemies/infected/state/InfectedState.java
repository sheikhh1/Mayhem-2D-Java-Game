package me.mayhem.game.entity.enemies.infected.state;


public enum InfectedState {
    //1 relates to x and 0 relates to y
    FORWARD(1),
    BACK(1),
    STANDING(1),
    JUMPING(0),
    FALLING(0),
    NO_MOTION(0),
    ;
    private int index;

    InfectedState(int index){this.index = index;}

    public int getIndex(){
        return this.index;
    }

}
